package com.andrew.messenger.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = {UserImageValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatInfo {

    String message() default "{Format image must be .jpg or .png}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
