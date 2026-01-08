package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CollectionItemRequest {
    @NotNull(message = "Provide product id")
    private Long productId;
}
