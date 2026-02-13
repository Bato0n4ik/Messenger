package com.andrew.messenger.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordDto {

    @NotBlank
    @Size(min = 8)
    private String newPassword;

    @NotBlank
    private String oldPassword;
}
