package com.sires.mp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCompraDTO {
    private Long Id;
    private String title;
    private String description;
    private String pictureUrl;
    private String category;
    private BigDecimal amount;
    private String currencyId;
    private BigDecimal unitPrice;
    private Integer quantity;

}
