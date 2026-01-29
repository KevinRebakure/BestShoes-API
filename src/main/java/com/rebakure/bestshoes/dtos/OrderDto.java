package com.rebakure.bestshoes.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderDto {
    private LocalDate placedOn;
    private Long id;
    private Long userId;
    private BigDecimal totalAmount;
    private String status;
}
