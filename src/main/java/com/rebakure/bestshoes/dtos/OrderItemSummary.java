package com.rebakure.bestshoes.dtos;

import lombok.Data;

@Data
public class OrderItemSummary {
    private int quantity;
    private Long variantId;
}
