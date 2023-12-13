package com.sires.mp.validations.interfaces;


import com.sires.mp.validations.CiValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CiValidator.class)
@Documented
public @interface Ci {
    String message() default "Número de cédula incorrecto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
