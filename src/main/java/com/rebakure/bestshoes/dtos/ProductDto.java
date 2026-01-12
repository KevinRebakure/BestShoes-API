package com.rebakure.bestshoes.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private String category;
    private String brand;
    private String material;
    private String color;
    private String size;
    private String stockKeepingUnit;
}
