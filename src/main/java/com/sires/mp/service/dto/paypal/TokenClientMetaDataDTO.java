package com.sires.mp.service.dto.paypal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenClientMetaDataDTO {
    private String name;
    private String display_name;
    @JsonProperty("logo_uri")
    private String logoUri;
    private List<String> scopes;
    @JsonProperty("ui_type")
    private String uiType;
}
