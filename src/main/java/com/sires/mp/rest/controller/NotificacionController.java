package com.sires.mp.rest.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.sires.mp.rest.ResponseEntityBuilder;
import com.sires.mp.service.PagoService;
import com.sires.mp.service.TokenService;
import com.sires.mp.service.dto.WebHookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificacionController {
    private final TokenService tokenService;
    private final PagoService pagoService;
    @GetMapping("/code")
    public String obtenerAutorizacion(@RequestParam("code") String code, @RequestParam("state") String status) throws MPException, MPApiException {
        tokenService.firstToken(code,status);
        return "Operación realizada correctamente!";
    }

    @PostMapping("/payment")
    public ResponseEntity<?> processPaymentMessages(@RequestBody(required = false) WebHookDTO dto) {
          if(dto.getData()!=null)
            return pagoService.processPayment(Long.parseLong(dto.getData().getId()));
          else return ResponseEntityBuilder.builder().status(HttpStatus.CREATED).build();
    }

    @PostMapping("/payment-notification")
    public ResponseEntity<?> processPaymentMessagesNoWebHook(@RequestParam(name="topic",required = false) String topic, @RequestParam(name="id",required = false) Long id) {
        log.info("Data:" + id);
        return pagoService.processPayment(id);
    }


}
