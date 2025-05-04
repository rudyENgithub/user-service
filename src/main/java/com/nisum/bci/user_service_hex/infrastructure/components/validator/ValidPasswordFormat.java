package com.nisum.bci.user_service_hex.infrastructure.components.validator;

import com.nisum.bci.user_service_hex.infrastructure.components.validator.impl.ValidatorPasswordFormat;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidatorPasswordFormat.class)
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPasswordFormat {
    String message() default "The password must be at least 8 characters long, include uppercase, lowercase, digits, and a special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
