package com.andrew.messenger.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class UserFileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private int maxFileSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        maxFileSize = constraintAnnotation.maxFileSize();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value.getSize() < maxFileSize;
    }
}
