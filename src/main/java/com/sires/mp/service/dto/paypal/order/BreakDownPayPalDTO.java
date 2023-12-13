package com.sires.mp.service.dto.paypal.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreakDownPayPalDTO {
    @JsonProperty("item_total")
    private UnitPricePayPalDTO itemTotal;
}
