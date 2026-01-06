package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotNull(message = "Category ID is required")
    private Integer categoryId;

    @NotNull(message = "Variant ID is required")
    private Long variantId;

    @NotBlank(message = "Name of the product is required")
    private String name;

    private String description;

    @NotNull(message = "Base price of the product is required")
    private BigDecimal basePrice;
}
