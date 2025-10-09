package com.orion.mdd.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.exception.custom.UserAlreadyExistsException;
import com.orion.mdd.mapper.UserMapper;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.LoginRequest;
import com.orion.mdd.payload.request.RegisterRequest;
import com.orion.mdd.payload.response.JwtResponse;
import com.orion.mdd.repository.UserRepository;
import com.orion.mdd.security.jwt.JwtUtils;
import com.orion.mdd.security.model.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtResponse authenticate(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            // user
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            User user = userPrincipal.getUser();

            String token = jwtUtils.generateJwtToken(user);

            return JwtResponse.builder()
                    .id(userPrincipal.getUser().getId())
                    .username(userPrincipal.getUser().getUsername())
                    .email(userPrincipal.getUser().getEmail())
                    .token(token)
                    .build();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Authentication failed: Invalid email or password");
        }

    }

    public UserDTO register(RegisterRequest registerRequest) {

        // check if user don't already exist
        String email = registerRequest.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exists!", email));
        }

        // add the new user
        User newUser = userMapper.toEntity(registerRequest);
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User newUserSaved = userRepository.save(newUser);

        return userMapper.tDto(newUserSaved);
    }

}
