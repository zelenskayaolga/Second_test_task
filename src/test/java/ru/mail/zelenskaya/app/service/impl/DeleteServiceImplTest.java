package ru.mail.zelenskaya.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mail.zelenskaya.app.repository.StudentRepository;

import static org.mockito.Mockito.when;
import static ru.mail.zelenskaya.app.service.impl.ServiceConstantTest.*;

@ExtendWith(MockitoExtension.class)
class DeleteServiceImplTest {

    @InjectMocks
    private DeleteServiceImpl deleteServiceImpl;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void shouldReturnAmountOfDeletedStringWhenWeDeleteStudentById() {
        when(studentRepository.deleteStudentById(TEST_ID)).thenReturn(ACTUAL_RESULT_FOR_DELETE);
        int methodCall = deleteServiceImpl.deleteStudentById(TEST_ID);
        Assertions.assertEquals(methodCall, ACTUAL_RESULT_FOR_DELETE);
    }
}