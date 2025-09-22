package com.orion.mdd.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orion.mdd.model.User;
import com.orion.mdd.repository.UserRepository;
import com.orion.mdd.security.model.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException(String.format("No user found for the email: %s", userEmail)));

        return new CustomUserDetails(user);
    }

}
