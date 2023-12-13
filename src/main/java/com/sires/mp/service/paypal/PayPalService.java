package com.sires.mp.service.paypal;

import com.sires.mp.service.dto.CarritoDTO;
import com.sires.mp.service.dto.paypal.TokenPayPalDTO;
import org.springframework.http.ResponseEntity;

public interface PayPalService {

    TokenPayPalDTO generateToken();
    ResponseEntity<?> generateOrder(CarritoDTO carrito);
}
