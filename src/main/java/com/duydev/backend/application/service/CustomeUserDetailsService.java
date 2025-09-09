package com.duydev.backend.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;
    
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetails = userRepository.findByUsername(username);
        if (userDetails == null) { 
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return userDetails;
    }
}
