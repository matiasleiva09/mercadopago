package com.sires.mp.rest.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.sires.mp.service.Preferencia;
import com.sires.mp.service.dto.CarritoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/preference")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreferenciaController {

    private final Preferencia service;

    @PostMapping
    public ResponseEntity<?> getAll(@RequestBody CarritoDTO carritoDTO) throws MPException, MPApiException {
        return service.createPreferencia(carritoDTO);
    }
}
