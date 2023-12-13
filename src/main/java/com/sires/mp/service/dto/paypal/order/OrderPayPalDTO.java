package com.sires.mp.service.dto.paypal.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPayPalDTO {
    private String intent;
    @JsonProperty("purcharse_units")
    private List<PurcharseUnitPayPalDTO> purcharseUnit;
}
