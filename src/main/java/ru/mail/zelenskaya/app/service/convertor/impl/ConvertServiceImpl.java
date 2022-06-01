package ru.mail.zelenskaya.app.service.convertor.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.mail.zelenskaya.app.repository.model.Group;
import ru.mail.zelenskaya.app.repository.model.Student;
import ru.mail.zelenskaya.app.service.SelectGroupService;
import ru.mail.zelenskaya.app.service.convertor.ConvertService;
import ru.mail.zelenskaya.app.service.model.StudentDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static ru.mail.zelenskaya.app.service.ServiceConstants.PATTERN_DATE;

@Component
@AllArgsConstructor
@Slf4j
public class ConvertServiceImpl implements ConvertService {
    private final SelectGroupService selectGroupService;

    @Override
    public StudentDTO convertToDto(Student student) {
        Integer numberOfGroup = student.getGroup().getGroupNumber();
        LocalDate dateOfBirth = student.getDateOfBirth();
        return StudentDTO.builder()
                .id(student.getId())
                .surname(student.getSurname())
                .name(student.getName())
                .patronymic(student.getPatronymic())
                .birth(String.valueOf(dateOfBirth))
                .groupNumber(numberOfGroup)
                .build();
    }

    @Override
    public List<StudentDTO> convertToListDTO(List<Student> students) {
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = convertToDto(student);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }

    @Override
    public Student convertToEntity(StudentDTO studentDTO) {
        LocalDate localDate = convertFromString(studentDTO.getBirth(), PATTERN_DATE);
        Group group = selectGroupService.getGroupByNumber(studentDTO.getGroupNumber());
        Student student = new Student();
        student.setSurname(studentDTO.getSurname());
        student.setName(studentDTO.getName());
        student.setPatronymic(studentDTO.getPatronymic());
        student.setDateOfBirth(localDate);
        student.setGroup(group);
        return student;
    }

    @Override
    public LocalDate convertFromString(String dateInString, String patternDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(patternDate, Locale.ENGLISH);
        try {
            Date date = formatter.parse(dateInString);
            return new java.sql.Date(date.getTime()).toLocalDate();
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
