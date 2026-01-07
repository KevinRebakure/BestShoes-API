package com.rebakure.bestshoes.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private Integer categoryId;
    private Long variantId;
    private String name;
    private String description;
    private BigDecimal basePrice;
}
