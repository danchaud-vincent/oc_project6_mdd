package com.orion.mdd.service;

import org.springframework.stereotype.Service;

import com.orion.mdd.payload.request.LoginRequest;
import com.orion.mdd.payload.request.RegisterRequest;
import com.orion.mdd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public String authenticate(LoginRequest loginRequest) {

        return "authenticated";
    }

    public String register(RegisterRequest registerRequest) {

        return "registered";
    }

}
