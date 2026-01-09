package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderItemRequest {
    @NotNull(message = "Provide the number of items")
    private Integer quantity;
}
