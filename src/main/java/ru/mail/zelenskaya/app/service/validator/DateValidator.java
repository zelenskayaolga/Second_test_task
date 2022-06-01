package ru.mail.zelenskaya.app.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static ru.mail.zelenskaya.app.service.ServiceConstants.MIN_YEAR_STUDENT;

public class DateValidator implements ConstraintValidator<NoMoreThanNow, String> {

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
            return LocalDate.parse(dateOfBirth).isBefore(LocalDate.now()) &&
                    LocalDate.parse(dateOfBirth).isBefore(LocalDate.now().minusYears(MIN_YEAR_STUDENT));
        }
        return false;
    }
}
