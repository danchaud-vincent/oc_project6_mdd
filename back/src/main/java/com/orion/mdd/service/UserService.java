package com.orion.mdd.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.exception.custom.ResourceNotFoundException;
import com.orion.mdd.mapper.UserMapper;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.UserRequest;
import com.orion.mdd.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO getUserById(Integer userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID %s Not Found", userId)));

        UserDTO userDTO = userMapper.tDto(existingUser);

        return userDTO;
    }

    @Transactional
    public UserDTO updateUserById(Integer userId, UserRequest userRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID %s Not Found", userId)));

        // update existing user
        userMapper.updateFromRequest(userRequest, user);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User userSaved = userRepository.save(user);

        return userMapper.tDto(userSaved);
    }

    @Transactional
    public void deleteUserById(Integer userId) {
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID %s Not Found", userId)));

        userRepository.delete(userToDelete);
    }

}
