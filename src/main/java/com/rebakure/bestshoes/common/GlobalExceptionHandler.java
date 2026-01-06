package com.rebakure.bestshoes.common;

import com.rebakure.bestshoes.dtos.ErrorDto;
import com.rebakure.bestshoes.dtos.MultipleErrorsDto;
import com.rebakure.bestshoes.exceptions.CategoryNotFoundException;
import com.rebakure.bestshoes.exceptions.VariantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCategoryNotFoundException(CategoryNotFoundException e) {
        ErrorDto errorDto = new ErrorDto(
                "Category Not Found",
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(VariantNotFoundException.class)
    public ResponseEntity<ErrorDto> handleVariantNotFoundException(VariantNotFoundException e) {
        ErrorDto errorDto = new ErrorDto(
                "Variant Not Found",
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleMissingRequestBody(
            HttpMessageNotReadableException ex) {
        ErrorDto errorDto = new ErrorDto(
                "Bad Request",
                HttpStatus.BAD_REQUEST.value(),
                "Request body is missing or malformed.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MultipleErrorsDto> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        MultipleErrorsDto errorDto = new MultipleErrorsDto(errors, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDto> handleIllegalStateException(IllegalStateException ex) {
        ErrorDto errorDto = new ErrorDto(
                "Invalid State",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        ErrorDto errorDto = new ErrorDto(
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Please try again later.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}