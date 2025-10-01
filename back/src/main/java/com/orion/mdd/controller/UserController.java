package com.orion.mdd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.payload.request.UserRequest;
import com.orion.mdd.payload.response.MessageResponse;
import com.orion.mdd.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {

        UserDTO userDTO = userService.getUserById(userId);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Integer userId, @RequestBody UserRequest userRequest) {

        UserDTO userUpdatedDTO = userService.updateUserById(userId, userRequest);

        return new ResponseEntity<UserDTO>(userUpdatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable Integer userId) {

        userService.deleteUserById(userId);

        return new ResponseEntity<MessageResponse>(
                new MessageResponse(String.format("User with ID %s deleted!", userId)), HttpStatus.OK);
    }

}
