package com.orion.mdd.service;

import org.springframework.stereotype.Service;

import com.orion.mdd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

}
