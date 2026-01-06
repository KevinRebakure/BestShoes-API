package com.rebakure.bestshoes.dtos;

import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.entities.Variant;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private Category category;
    private Variant variant;
    private String name;
    private String description;
    private BigDecimal basePrice;
}
