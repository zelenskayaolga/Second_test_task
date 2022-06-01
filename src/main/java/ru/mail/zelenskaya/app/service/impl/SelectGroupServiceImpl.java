package ru.mail.zelenskaya.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.zelenskaya.app.repository.GroupRepository;
import ru.mail.zelenskaya.app.repository.model.Group;
import ru.mail.zelenskaya.app.service.SelectGroupService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SelectGroupServiceImpl implements SelectGroupService {
    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public Group getGroupByNumber(Integer groupNumber) {
        Optional<Group> groupByNumber = groupRepository.findGroupByGroupNumber(groupNumber);
        return groupByNumber.orElse(null);
    }
}
