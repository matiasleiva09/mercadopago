package com.sires.mp.rest.controller.paypal;

import com.sires.mp.rest.ResponseEntityBuilder;
import com.sires.mp.service.dto.WebHookDTO;
import com.sires.mp.service.paypal.PayPalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paypal")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PayPalController {
    private final PayPalService service;

    @PostMapping("/auth")
    public ResponseEntity<?> auth() {
      return ResponseEntityBuilder.builder().data(service.generateToken()).build();
    }
}
