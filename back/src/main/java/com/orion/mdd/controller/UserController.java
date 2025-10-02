package com.orion.mdd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.payload.request.UserRequest;
import com.orion.mdd.payload.response.JwtResponse;
import com.orion.mdd.payload.response.MessageResponse;
import com.orion.mdd.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMeUser(Authentication authentication) {

        UserDTO userDTO = userService.getMeUser(authentication);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/me")
    public ResponseEntity<JwtResponse> updateMeUser(Authentication authentication,
            @RequestBody UserRequest userRequest) {

        JwtResponse jwtResponse = userService.updateMeUser(authentication, userRequest);

        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
    }

    @DeleteMapping("/me")
    public ResponseEntity<MessageResponse> deleteMeUser(Authentication authentication) {

        String emailUser = userService.deleteMeUser(authentication);

        return new ResponseEntity<MessageResponse>(
                new MessageResponse(String.format("User with email %s deleted!", emailUser)), HttpStatus.OK);
    }

}
