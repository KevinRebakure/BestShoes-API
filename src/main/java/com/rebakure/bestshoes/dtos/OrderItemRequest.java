package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {
    @NotNull(message = "Select a product")
    private Long productId;

    @NotNull(message = "Set the number of items you want")
    private Integer quantity;
}
