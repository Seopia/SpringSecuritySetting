package com.bu200.bu200.security.service;

import com.bu200.bu200.security.dto.CustomUserDetails;
import com.bu200.bu200.security.entity.User;
import com.bu200.bu200.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userData = userRepository.findByAccountId(username);
        if(userData != null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
