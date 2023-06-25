package com.sires.mp.service.impl;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.sires.mp.domain.MercadoPagoPayment;
import com.sires.mp.repository.PagoRespository;
import com.sires.mp.rest.ResponseEntityBuilder;
import com.sires.mp.rest.exception.BadRequestException;
import com.sires.mp.service.PagoService;
import com.sires.mp.service.TokenService;
import com.sires.mp.service.mapper.MapperApp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PagoServiceImpl implements PagoService {
    private final TokenService tokenService;
    private final PagoRespository repository;

    @Override
    public Payment getPayment(Long id) throws BadRequestException {

        try {
            String token = tokenService.obtainLastToken();
            return new PaymentClient().get(id, MPRequestOptions.builder()
                    .accessToken(token).build());

        } catch (HttpStatusCodeException | MPApiException | MPException | BadRequestException ex) {
            log.error(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> processPayment(Long id) {
        try {
            Payment mpPayment = getPayment(id);
            if (mpPayment != null) {
                log.info("Llegó la transacción: " + mpPayment.getId());
                log.info("Comisión MP: " + mpPayment.getFeeDetails().get(0).getAmount());
                MercadoPagoPayment payment = MapperApp.INSTANCE.fromEntityToDTO(mpPayment);
                log.info("Ingreso:" + payment.getTotalFeeAmount());
                repository.save(payment);
                log.info("monto:" + payment.getNetReceivedAmount() + ", fecha creado:" + payment.getDateCreated());
                return ResponseEntityBuilder.builder().status(HttpStatus.CREATED).build();

            } else {
                log.error("No existe el pago con id: " + id);
                //  throw new BadRequestException("No existe el pago con id: " + id);
                return ResponseEntityBuilder.builder().status(HttpStatus.CREATED).build();
            }
        } catch (BadRequestException ex) {
            return ResponseEntityBuilder.builder().status(HttpStatus.CREATED).build();
        }

    }

    @Override
    public ResponseEntity<?> getPagosBetweenDate(String fechaDesde, String fechaHasta) {
        log.info("obteniendo los pagos por fecha");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
       try{
           return ResponseEntityBuilder.builder().data(repository.getPagos(formato.parse(fechaDesde), formato.parse(fechaHasta)))
                   .status(HttpStatus.OK)
                   .build();
        }
       catch(Exception e){
           e.printStackTrace();
           throw new BadRequestException("Ingrese fechas correctamente");
       }

    }
}
