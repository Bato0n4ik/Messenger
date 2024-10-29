package com.andrew.messenger.dto;

import com.andrew.messenger.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;


import java.time.LocalDate;

@Value
public class UserCreateEditDto {

    @Email
    String username;

    LocalDate birthDate;

    @NotNull
    @Size(min = 2, max = 64)
    String firstname;

    @NotNull
    @Size(min = 2, max = 64)
    String lastname;
    Role role;
}