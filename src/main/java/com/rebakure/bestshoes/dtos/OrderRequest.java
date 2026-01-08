package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
}