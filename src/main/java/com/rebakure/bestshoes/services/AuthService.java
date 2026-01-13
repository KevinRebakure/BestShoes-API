package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.RegisterUserRequest;
import com.rebakure.bestshoes.dtos.UserDto;
import com.rebakure.bestshoes.entities.User;
import com.rebakure.bestshoes.exceptions.ConflictException;
import com.rebakure.bestshoes.mappers.UserMapper;
import com.rebakure.bestshoes.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto register(RegisterUserRequest request) {
        var user = userRepository.findUserByEmail(request.getEmail());

        if (user != null) {
            throw new ConflictException("User with this email already exists");
        }

        var newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setName(request.getName());

        userRepository.save(newUser);
        return userMapper.entityToDto(newUser);
    }
}
