package ru.mail.zelenskaya.app.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupNumberValidator.class)
public @interface IsNumber {
    String message() default "The group with the specified number was not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
