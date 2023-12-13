package com.sires.mp.service.impl.paypal;

import com.sires.mp.service.dto.CarritoDTO;
import com.sires.mp.service.dto.paypal.TokenPayPalDTO;
import com.sires.mp.service.paypal.PayPalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PayPalServiceImpl implements PayPalService {
    private final RestTemplate template;
    @Override
    public TokenPayPalDTO generateToken() {

        String plainCreds = "AaeFgrdy5Oz7HUwu7pUYQXxbX36j10fkFzUGvvvcx2zKYMR2sebOkeZkwW1SI9GQKi65uUuCMqUV7X_S:EC5pMlgtW9XWiGpK3KZF8aWIbigx77kQDP8ck541PxqDf63aBIcFqBftQ-VKrd6B4knyJkp2COKNvXyW";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","client_credentials");
        map.add("ignoreCache","true");
        map.add("return_authn_schemes","true");
        map.add("return_client_metadata","true");
        map.add("return_unconsented_scopes","true");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<TokenPayPalDTO> response = template.exchange("https://api-m.sandbox.paypal.com/v1/oauth2/token", HttpMethod.POST, request, TokenPayPalDTO.class);
        return response.getBody();
    }

    @Override
    public ResponseEntity<?> generateOrder(CarritoDTO carrito) {
        return null;
    }
}
