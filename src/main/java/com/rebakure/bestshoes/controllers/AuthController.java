package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.JwtResponse;
import com.rebakure.bestshoes.dtos.LoginRequest;
import com.rebakure.bestshoes.dtos.RegisterUserRequest;
import com.rebakure.bestshoes.dtos.UserDto;
import com.rebakure.bestshoes.mappers.UserMapper;
import com.rebakure.bestshoes.repositories.UserRepository;
import com.rebakure.bestshoes.services.AuthService;
import com.rebakure.bestshoes.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService  jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody RegisterUserRequest request) {
        var dto = authService.register(request);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var token = jwtService.generateToken(request.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = (String) authentication.getPrincipal();
        var user = userRepository.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var userDto = userMapper.entityToDto(user);

        return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
