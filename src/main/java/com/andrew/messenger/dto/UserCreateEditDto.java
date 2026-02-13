package com.andrew.messenger.dto;

import com.andrew.messenger.database.entity.Role;
import com.andrew.messenger.validation.FileSize;
import com.andrew.messenger.validation.FormatInfo;
import com.andrew.messenger.validation.groups.OnCreate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {

    @Email
    String username;

    @NotBlank(groups = {OnCreate.class})
    String rawPassword;

    LocalDate birthDate;

    @NotNull
    @Size(min = 2, max = 64)
    String firstname;

    @NotNull
    @Size(min = 2, max = 64)
    String lastname;

    Role role;

    @FormatInfo
    @FileSize
    MultipartFile image;
}
