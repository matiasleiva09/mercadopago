package com.sires.mp.service.dto.paypal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenPayPalDTO {
    private String scope;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("supported_authn_schemes")
    private List<String> supportedAuthSchemes;
    private String nonce;
    @JsonProperty("client_metadata")
    private TokenClientMetaDataDTO clientMetaData;

}
