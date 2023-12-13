package com.sires.mp.service.impl;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.sires.mp.domain.TokenMP;
import com.sires.mp.repository.TokenRepository;
import com.sires.mp.rest.exception.BadRequestException;
import com.sires.mp.service.ConfiguracionService;
import com.sires.mp.service.TokenService;
import com.sires.mp.service.dto.LoginResponseDTO;
import com.sires.mp.service.dto.TokenMPDTO;
import com.sires.mp.service.mapper.MapperApp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {
    @Value("${ml.auth.token.url}")
    private String tokenUrl;
    private final ConfiguracionService configuracionService;
    private final RestTemplate template;
    private final TokenRepository repository;



    @Override
    public boolean firstToken(String Code, String status)  {
        log.info("Viene code: " + Code);
        log.info("Viene status:" + status);
        boolean resultado = false;
        if (Code != null && !Code.equalsIgnoreCase("") && status != null && !status.equalsIgnoreCase("")) {
            HashMap<String, String> requestParam = new HashMap<String, String>();
            requestParam.put("client_secret", configuracionService.findByKey("app.client-secret").getValue());
            requestParam.put("client_id",  configuracionService.findByKey("app.client-id").getValue());
            boolean modoTestToken = Boolean.parseBoolean(configuracionService.findByKey("in.test.mode").getValue());
            if (modoTestToken)
                requestParam.put("test_token", modoTestToken + "");
            requestParam.put("grant_type", "authorization_code");
            requestParam.put("redirect_uri", configuracionService.findByKey("token.redirect-uri").getValue());

            requestParam.put("code", Code);
            log.info("consumiendo endpoint de auth de mercado pago");
            LoginResponseDTO loginResponseDTO = template.exchange(tokenUrl, HttpMethod.POST, new HttpEntity<>(requestParam),
                    LoginResponseDTO.class).getBody();
            TokenMP datoGuardar = MapperApp.INSTANCE.fromEntityToDTO(loginResponseDTO);
            datoGuardar.setDateTime(new Date());
            repository.save(datoGuardar);
            log.info("****listo");

            return resultado;
        } else {
            throw new BadRequestException(("Complete los campos obligatorios"));
        }
    }

    @Override
    public String obtainLastToken() throws MPException, MPApiException {

        TokenMP ultimoToken = repository.obtenerUltimoToken();
        String tokenVivo = null;
        if (ultimoToken != null) {
            int diasExpiracionToken = (int) TimeUnit.SECONDS.toDays(ultimoToken.getExpire());
            Calendar fechaCaducidad = Calendar.getInstance();
            fechaCaducidad.setTime(ultimoToken.getDateTime());
            fechaCaducidad.add(Calendar.DATE, diasExpiracionToken);
            long fechaActual = new Date().getTime();

            if (fechaActual >= fechaCaducidad.getTime().getTime()) {
                LoginResponseDTO nuevoToken = refreshToken(ultimoToken.getRefreshToken());
                repository.save(MapperApp.INSTANCE.fromEntityToDTO(nuevoToken));
                tokenVivo = nuevoToken.getAccessToken();
            } else
                tokenVivo = ultimoToken.getAccessToken();
        }
        return tokenVivo;
    }



    /*
        EL TOKEN SE VENCE CADA 180 DIAS, UNA VEZ PASADO ESE TIEMPO SE LLAMA A ESTE METODO
        PARA REFRESCARLO
     */

    @Override
    public LoginResponseDTO refreshToken(String refreshToken) throws MPException, MPApiException {

        HashMap<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("client_secret",  configuracionService.findByKey("app.client-secret").getValue());
        requestParam.put("client_id",  configuracionService.findByKey("app.client-id").getValue());
        boolean modoTestToken = Boolean.parseBoolean(configuracionService.findByKey("in.test.mode").getValue());
        if (modoTestToken)
            requestParam.put("test_token", "true");
        requestParam.put("refresh_token", refreshToken);
        requestParam.put("grant_type", "refresh_token");

        LoginResponseDTO loginResponseDTO = template.exchange(tokenUrl, HttpMethod.POST, new HttpEntity<>(requestParam),
                LoginResponseDTO.class).getBody();
        return loginResponseDTO;
    }


}
