package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.RegisterUserRequest;
import com.rebakure.bestshoes.dtos.UserDto;
import com.rebakure.bestshoes.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(
            @Valid @RequestBody RegisterUserRequest request) {
        var dto = authService.register(request);
        return ResponseEntity.ok(dto);
    }
}
