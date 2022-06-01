package ru.mail.zelenskaya.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mail.zelenskaya.app.repository.GroupRepository;
import ru.mail.zelenskaya.app.repository.model.Group;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static ru.mail.zelenskaya.app.service.impl.ServiceConstantTest.*;

@ExtendWith(MockitoExtension.class)
class SelectGroupServiceImplTest {
    @InjectMocks
    private SelectGroupServiceImpl selectGroupService;

    @Mock
    private GroupRepository groupRepository;

    @Test
    void shouldReturnGroupWhenWeFindGroupByNumber() {
        Group group = new Group();
        group.setId(TEST_ID);
        group.setGroupNumber(TEST_GROUP_NUMBER);

        when(groupRepository.findGroupByGroupNumber(TEST_GROUP_NUMBER)).thenReturn(Optional.of(group));
        Group groupByNumber = selectGroupService.getGroupByNumber(TEST_GROUP_NUMBER);
        Assertions.assertEquals(groupByNumber.getId(), TEST_ID);
    }
}