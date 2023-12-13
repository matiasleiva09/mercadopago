package com.sires.mp.service.dto.paypal.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPayPalDTO {
    private String name;
    private String description;
    private String quantity;
    @JsonProperty("unit_amount")
    private UnitPricePayPalDTO unitAmount;

}
