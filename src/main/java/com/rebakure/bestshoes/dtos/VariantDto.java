package com.rebakure.bestshoes.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VariantDto {
    private String brand;
    private String color;
    private String material;
    private String size;
    private BigDecimal price;
    private Integer quantity;
    private String stockKeepingUnit;
}
