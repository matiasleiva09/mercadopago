package com.sires.mp.service.dto.paypal.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurcharseUnitPayPalDTO {
    @JsonProperty("items")
    private List<ItemPayPalDTO> items;
    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("invoice_id")
    private String invoiceId;
    @JsonProperty("custom_id")
    private String customId;
    @JsonProperty("breakdown")
    private BreakDownPayPalDTO breakDown;
}
