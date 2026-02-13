package com.andrew.messenger.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class UserImageValidator implements ConstraintValidator<FormatInfo, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return true;
        }
        return value.getOriginalFilename().endsWith(".jpg") || value.getOriginalFilename().endsWith(".png");
    }
}
