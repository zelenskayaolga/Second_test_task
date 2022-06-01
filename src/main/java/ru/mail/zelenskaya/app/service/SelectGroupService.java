package ru.mail.zelenskaya.app.service;

import ru.mail.zelenskaya.app.repository.model.Group;

public interface SelectGroupService {
    Group getGroupByNumber(Integer groupNumber);
}
