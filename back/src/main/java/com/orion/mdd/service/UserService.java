package com.orion.mdd.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.exception.custom.ResourceNotFoundException;
import com.orion.mdd.mapper.UserMapper;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.UserRequest;
import com.orion.mdd.payload.response.JwtResponse;
import com.orion.mdd.repository.UserRepository;
import com.orion.mdd.security.jwt.JwtUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UserDTO getMeUser(Authentication authentication) {
        // Get the user principal
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String emailPrincipal = jwt.getClaim("sub");

        User userPrincipal = userRepository.findByEmail(emailPrincipal)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with email %s Not Found", emailPrincipal)));

        UserDTO userDTO = userMapper.tDto(userPrincipal);

        return userDTO;
    }

    @Transactional
    public JwtResponse updateMeUser(Authentication authentication, UserRequest userRequest) {
        // Get the user principal
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String emailPrincipal = jwt.getClaim("sub");

        User userPrincipal = userRepository.findByEmail(emailPrincipal)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with email %s Not Found", emailPrincipal)));

        // update existing user
        userMapper.updateFromRequest(userRequest, userPrincipal);
        userPrincipal.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User userSaved = userRepository.save(userPrincipal);

        // Generate a new token for the new email
        String newToken = jwtUtils.generateJwtToken(userSaved);

        return JwtResponse.builder()
                .id(userSaved.getId())
                .username(userSaved.getUsername())
                .email(userSaved.getEmail())
                .token(newToken)
                .build();
    }

    @Transactional
    public String deleteMeUser(Authentication authentication) {
        // Get the user principal
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String emailPrincipal = jwt.getClaim("sub");

        User userPrincipal = userRepository.findByEmail(emailPrincipal)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with email %s Not Found", emailPrincipal)));

        userRepository.delete(userPrincipal);

        return emailPrincipal;
    }

}
