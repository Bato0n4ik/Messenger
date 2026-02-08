package com.andrew.messenger.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {UserFileSizeValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {

    String message() default "{File size must be less then 5Mb}";

    int maxFileSize() default 5242880;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
