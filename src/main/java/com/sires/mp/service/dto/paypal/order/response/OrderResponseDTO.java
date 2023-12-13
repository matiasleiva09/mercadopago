package com.sires.mp.service.dto.paypal.order.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDTO {
    private String id;
    private String status;
    private List<LinkOrderResponseDTO> links;
}
