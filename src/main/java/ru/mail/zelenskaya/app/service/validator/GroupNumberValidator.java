package ru.mail.zelenskaya.app.service.validator;

import ru.mail.zelenskaya.app.repository.GroupRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupNumberValidator implements ConstraintValidator<IsNumber, Integer> {
    private final GroupRepository groupRepository;

    public GroupNumberValidator(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public boolean isValid(Integer groupNumber, ConstraintValidatorContext constraintValidatorContext) {
        return groupRepository.existsGroupByGroupNumber(groupNumber);
    }
}
