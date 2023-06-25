package com.sires.mp.rest.controller;

import com.sires.mp.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/processed-payment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PagoController {
    private final PagoService pagoService;

    @GetMapping("/between/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<?> getBetween(@PathVariable("fechaDesde") String fechaDesde, @PathVariable("fechaHasta") String fechaHasta) {
       return pagoService.getPagosBetweenDate(fechaDesde,fechaHasta);
    }
}
