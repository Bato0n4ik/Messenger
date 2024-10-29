package com.andrew.messenger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class LoginDto {
    @Email
    String username;

    @NotNull
    @Size(min = 8, max = 30)
    String password;
}
