package com.andrew.messenger.validators;

import com.andrew.messenger.dto.LoginDto;
import com.andrew.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginValidator {

    private final UserService userService;

    public boolean validate(LoginDto loginDto) {
        return userService.findByUsername(loginDto.getUsername())
                .map(u -> {
                    return u.getPassword().equals(loginDto.getPassword());
                })
                .orElse(false);
    }
}
