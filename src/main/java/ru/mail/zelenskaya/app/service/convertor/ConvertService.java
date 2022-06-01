package ru.mail.zelenskaya.app.service.convertor;

import ru.mail.zelenskaya.app.repository.model.Student;
import ru.mail.zelenskaya.app.service.model.StudentDTO;

import java.time.LocalDate;
import java.util.List;

public interface ConvertService {
    StudentDTO convertToDto(Student student);

    List<StudentDTO> convertToListDTO(List<Student> students);

    Student convertToEntity(StudentDTO studentDTO);

    LocalDate convertFromString(String dateInString, String patternDate);
}
