package com.sires.mp.service.dto.paypal.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmountOrderPayPalDTO {
    @JsonProperty("currency_code")
    private String currencyCode;
    private BigDecimal value;

}
