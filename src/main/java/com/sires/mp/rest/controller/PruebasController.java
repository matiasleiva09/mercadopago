package com.sires.mp.rest.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.sires.mp.rest.ResponseEntityBuilder;
import com.sires.mp.service.dto.CarritoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejemplo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PruebasController {
    @GetMapping
    public ResponseEntity<?> getAll(@RequestBody @Valid EjemploDTO ejemplo) throws MPException, MPApiException {
        log.info("hola");
        return ResponseEntityBuilder.builder().status(HttpStatus.OK).build();
    }
}
