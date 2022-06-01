package ru.mail.zelenskaya.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.mail.zelenskaya.app.repository.GroupRepository;
import ru.mail.zelenskaya.app.repository.StudentRepository;
import ru.mail.zelenskaya.app.repository.model.Group;
import ru.mail.zelenskaya.app.repository.model.Student;
import ru.mail.zelenskaya.app.service.SelectGroupService;
import ru.mail.zelenskaya.app.service.convertor.ConvertService;
import ru.mail.zelenskaya.app.service.model.StudentDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.mockito.Mockito.when;
import static ru.mail.zelenskaya.app.service.impl.ServiceConstantTest.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CreateServiceImplTest {

    @InjectMocks
    private CreateServiceImpl createService;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ConvertService convertService;
    @Mock
    private SelectGroupService selectGroupService;
    @Mock
    private GroupRepository groupRepository;


    @Test
    void shouldReturnStudentDTOWhenValidInput() throws ParseException {
        Group group = new Group();
        group.setId(TEST_ID);
        group.setGroupNumber(TEST_GROUP_NUMBER);

        StudentDTO studentDTO = StudentDTO.builder()
                .id(TEST_ID)
                .surname(TEST_SURNAME)
                .name(TEST_NAME)
                .patronymic(TEST_PATRONYMIC)
                .birth(TEST_DATE_OF_BIRTH)
                .groupNumber(TEST_GROUP_NUMBER)
                .build();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_DATE);
        Date date = simpleDateFormat.parse(TEST_DATE_OF_BIRTH);
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();

        Student student = new Student();
        student.setSurname(TEST_SURNAME);
        student.setName(TEST_NAME);
        student.setPatronymic(TEST_PATRONYMIC);
        student.setDateOfBirth(localDate);
        student.setGroup(group);

        Student addedStudent = new Student();
        addedStudent.setId(TEST_ID);
        addedStudent.setSurname(TEST_SURNAME);
        addedStudent.setName(TEST_NAME);
        addedStudent.setPatronymic(TEST_PATRONYMIC);
        addedStudent.setDateOfBirth(localDate);
        addedStudent.setGroup(group);

        when(selectGroupService.getGroupByNumber(TEST_GROUP_NUMBER)).thenReturn(group);
        when(convertService.convertToEntity(studentDTO)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(addedStudent);
        when(convertService.convertToDto(addedStudent)).thenReturn(studentDTO);
        StudentDTO save = createService.addStudent(studentDTO);
        Assertions.assertEquals(save, studentDTO);
    }
}