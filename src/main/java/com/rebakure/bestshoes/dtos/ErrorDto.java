package com.rebakure.bestshoes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDto {
    private final String error;
    private final Integer status;
    private final String message;
    private final LocalDateTime timestamp;
}
