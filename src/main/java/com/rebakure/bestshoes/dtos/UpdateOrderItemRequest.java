package com.rebakure.bestshoes.dtos;

import lombok.Data;

@Data
public class UpdateOrderItemRequest {
    private Long productId;
    private Integer quantity;
}
