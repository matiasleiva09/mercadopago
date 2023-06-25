package com.sires.mp.service;

import com.mercadopago.resources.payment.Payment;
import com.sires.mp.domain.MercadoPagoPayment;
import com.sires.mp.rest.exception.BadRequestException;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface PagoService {
    Payment getPayment(Long id) throws BadRequestException;
    ResponseEntity<?> processPayment(Long id) throws BadRequestException;
    ResponseEntity<?> getPagosBetweenDate(String fechaDesde, String fechaHasta);
}
