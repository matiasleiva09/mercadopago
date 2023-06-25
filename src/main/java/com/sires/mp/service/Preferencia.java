package com.sires.mp.service;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.sires.mp.service.dto.CarritoDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface Preferencia {
     ResponseEntity<?> createPreferencia(CarritoDTO carrito) throws MPException, MPApiException;
}
