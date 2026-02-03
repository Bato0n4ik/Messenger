package com.andrew.messenger.validators;

import com.andrew.messenger.dto.LoginDto;
import com.andrew.messenger.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginValidator {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public boolean validate(LoginDto loginDto) {
        return userService.findByUsername(loginDto.getUsername())
                .map(u -> u.getPassword().equals(passwordEncoder.encode(loginDto.getPassword())))
                .orElse(false);
    }
}
