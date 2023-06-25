package com.sires.mp.service;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.sires.mp.domain.TokenMP;
import com.sires.mp.rest.exception.BadRequestException;
import com.sires.mp.service.dto.LoginResponseDTO;
import com.sires.mp.service.dto.TokenMPDTO;

public interface TokenService {
    boolean firstToken(String Code,String status) throws MPException, MPApiException;
    String obtainLastToken() throws MPException, MPApiException;
    LoginResponseDTO refreshToken(String refreshToken) throws MPException, MPApiException;
}
