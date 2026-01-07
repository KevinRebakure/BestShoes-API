package com.rebakure.bestshoes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationErrorsResponse {
    private final Map<String,String> errors;
    private final Integer status;
    private final LocalDateTime timestamp;
}
