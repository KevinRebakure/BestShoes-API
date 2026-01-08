package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CollectionRequest {
    @NotBlank(message = "Provide the name")
    private String name;
}
