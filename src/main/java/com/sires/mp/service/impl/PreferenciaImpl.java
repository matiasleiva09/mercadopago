package com.sires.mp.service.impl;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.sires.mp.rest.ResponseEntityBuilder;
import com.sires.mp.service.ConfiguracionService;
import com.sires.mp.service.Preferencia;
import com.sires.mp.service.dto.CarritoDTO;
import com.sires.mp.service.dto.ConfigurationDTO;
import com.sires.mp.service.dto.ResponsePreferenceDTO;
import com.sires.mp.service.mapper.MapperApp;
import com.sires.mp.service.utils.ConfiguracionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreferenciaImpl implements Preferencia {
    private PreferenceBackUrlsRequest urlsRequest;
    private List<ConfigurationDTO> configuraciones;
    private List<String> mediosPago = null;
    private final ConfiguracionService dinamoService;
    private List<String> tiposDeMedioPago = null;

    private void config() {
        log.info("createPreferencia=> trayendo las configuraciones");
        log.info("createPreferencia = > fin trayendo las configuraciones");
        configuraciones = dinamoService.getAllConfiguration();
        MercadoPagoConfig.setAccessToken(ConfiguracionUtils.getValueFromKey(configuraciones, "app.access.token"));
        urlsRequest = PreferenceBackUrlsRequest.builder()
                .success(ConfiguracionUtils.getValueFromKey(configuraciones, "app.redirect.ok.url"))
                .failure(ConfiguracionUtils.getValueFromKey(configuraciones, "app.redirect.failure.url"))
                .pending(ConfiguracionUtils.getValueFromKey(configuraciones, "app.redirect.pending.url"))
                .build();
        mediosPago = Arrays.stream(ConfiguracionUtils.getValueFromKey(configuraciones, "preferencia.exclude.payment.method").split(",")).toList();
        tiposDeMedioPago= Arrays.stream(ConfiguracionUtils.getValueFromKey(configuraciones, "preference.payment.permited").split(",")).toList();
    }

    @Override
    public ResponseEntity<?> createPreferencia(CarritoDTO carrito) throws MPException, MPApiException {
        config();
        boolean expira = Boolean.parseBoolean(ConfiguracionUtils.getValueFromKey(configuraciones, "preferencia.limite.tiempo.hab"));
        OffsetDateTime tiempoDesde = null;
        OffsetDateTime tiempoHasta = null;
        if(expira){
            tiempoDesde = OffsetDateTime.now();
            tiempoHasta = tiempoDesde.plusMinutes(Integer.parseInt(ConfiguracionUtils.getValueFromKey(configuraciones, "preferencia.limite.tiempo")));
        }
        boolean binario = Boolean.parseBoolean(ConfiguracionUtils.getValueFromKey(configuraciones, "preferencia.binary.mode"));
        List<PreferencePaymentTypeRequest> lista = new ArrayList<PreferencePaymentTypeRequest>();
        List<PreferencePaymentMethodRequest> listaMediosDePagoExcluidos = new ArrayList<PreferencePaymentMethodRequest>();
        if(mediosPago!=null && mediosPago.size() > 0 ){
            mediosPago.forEach(tm -> {
                log.info("Medio: " + tm);
                lista.add(PreferencePaymentTypeRequest.builder().id(tm).build());
            });
        }
        if(tiposDeMedioPago!=null && tiposDeMedioPago.size() > 0){
            tiposDeMedioPago.forEach(tp->{
                listaMediosDePagoExcluidos.add(PreferencePaymentMethodRequest.builder().id(tp).build());
            });
        }

        log.info("Fin metodos de pago deshabilitados");
        boolean autoReturn = Boolean.parseBoolean(
                ConfiguracionUtils.getValueFromKey(configuraciones, "auto.return.activated"));
        PreferenceClient client = new PreferenceClient();
        MercadoPagoConfig.setIntegratorId(ConfiguracionUtils.getValueFromKey(configuraciones, "integrator.id"));
        PreferenceRequest request = PreferenceRequest.builder()
                .items(MapperApp.INSTANCE.fromEntityToDTO(carrito.getItems()))
                .backUrls(urlsRequest)
                .binaryMode(binario)
                .notificationUrl(ConfiguracionUtils.getValueFromKey(configuraciones, "preferencia.notification.url"))
                .expires(expira)
                .payer(MapperApp.INSTANCE.fromEntityToDTO(carrito.getUsuario()))
                .expirationDateFrom(tiempoDesde)
                .dateOfExpiration(tiempoHasta)
                .autoReturn((autoReturn) ? ConfiguracionUtils.getValueFromKey(configuraciones, "auto.return"): null)
                .externalReference(carrito.getId())
                .paymentMethods(PreferencePaymentMethodsRequest
                        .builder()
                        .installments(Integer.parseInt(ConfiguracionUtils.getValueFromKey(configuraciones, "preferencia.cuotas")))
                        .defaultInstallments(Integer.parseInt(ConfiguracionUtils.getValueFromKey(configuraciones, "preferencia.cuotas")))
                        .excludedPaymentTypes(lista)
                        .excludedPaymentMethods(listaMediosDePagoExcluidos)
                        .build())
                .build();
        ResponsePreferenceDTO response = MapperApp.INSTANCE.fromEntityToDTO(client.create(request));
        return ResponseEntityBuilder.builder().data(response).status(HttpStatus.OK).build();
    }
}
