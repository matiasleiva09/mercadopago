package com.sires.mp.service.dto.paypal.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkOrderResponseDTO {
    private String href;
    private String rel;
    private String method;
}
