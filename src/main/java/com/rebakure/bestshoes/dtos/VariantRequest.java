package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VariantRequest {
    @NotBlank(message = "Provide the brand name of the shoe")
    private String brand;

    @NotBlank(message = "Provide the color of the shoe")
    private String color;

    @NotBlank(message = "Provide the material of the shoe")
    private String material;

    @NotBlank(message = "Provide the size of the shoe")
    private String size;

    @NotNull(message = "Provide the price of the shoe")
    private BigDecimal price;

    @NotNull(message = "Provide the number of shoes in store")
    private Integer quantity;

    @NotBlank(message = "Provide the stock keeping unit")
    private String stockKeepingUnit;
}
