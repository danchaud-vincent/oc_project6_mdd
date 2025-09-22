package com.orion.mdd.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.mapper.UserMapper;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.LoginRequest;
import com.orion.mdd.payload.request.RegisterRequest;
import com.orion.mdd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(LoginRequest loginRequest) {

        return "authenticated";
    }

    public UserDTO register(RegisterRequest registerRequest) {

        // check if user don't already exist
        String email = registerRequest.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            throw new RuntimeException(String.format("User with email %s already exists!", email));
        }

        // add the new user
        User newUser = userMapper.toEntity(registerRequest);
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User newUserSaved = userRepository.save(newUser);

        return userMapper.tDto(newUserSaved);
    }

}
