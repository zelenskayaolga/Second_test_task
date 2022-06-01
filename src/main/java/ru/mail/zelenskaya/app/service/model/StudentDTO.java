package ru.mail.zelenskaya.app.service.model;

import lombok.Builder;
import lombok.Data;
import ru.mail.zelenskaya.app.service.validator.IsNumber;
import ru.mail.zelenskaya.app.service.validator.NoMoreThanNow;

import javax.validation.constraints.*;

import static ru.mail.zelenskaya.app.service.ServiceConstants.*;

@Data
@Builder
public class StudentDTO {
    private Long id;
    @Size(min = MIN_SIZE, max = MAX_SIZE, message = MESSAGE_SIZE_SURNAME)
    @NotBlank(message = MESSAGE_NOT_BLANK_SURNAME)
    private String surname;
    @Size(min = MIN_SIZE, max = MAX_SIZE, message = MESSAGE_SIZE_NAME)
    @NotBlank(message = MESSAGE_NOT_BLANK_NAME)
    private String name;
    @Size(min = MIN_SIZE, max = MAX_SIZE, message = MESSAGE_SIZE_PATRONYMIC)
    @NotBlank(message = MESSAGE_NOT_BLANC_PATRONYMIC)
    private String patronymic;
    @NoMoreThanNow
    private String birth;
    @IsNumber
    private Integer groupNumber;
}
