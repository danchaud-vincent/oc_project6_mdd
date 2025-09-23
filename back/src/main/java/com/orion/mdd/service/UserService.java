package com.orion.mdd.service;

import org.springframework.stereotype.Service;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.exception.custom.ResourceNotFoundException;
import com.orion.mdd.mapper.UserMapper;
import com.orion.mdd.model.User;
import com.orion.mdd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO getUserById(Integer userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID %s Not Found", userId)));

        UserDTO userDTO = userMapper.tDto(existingUser);

        return userDTO;
    }

}
