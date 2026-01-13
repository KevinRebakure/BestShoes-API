package com.rebakure.bestshoes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Provide the email")
    @Email(message = "Provide a valid email")
    private String email;

    @NotBlank(message = "Provide the password")
    private String password;
}
