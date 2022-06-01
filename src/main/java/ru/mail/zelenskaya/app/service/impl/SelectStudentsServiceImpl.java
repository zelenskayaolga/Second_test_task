package ru.mail.zelenskaya.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.zelenskaya.app.repository.StudentRepository;
import ru.mail.zelenskaya.app.repository.model.Student;
import ru.mail.zelenskaya.app.service.SelectStudentsService;
import ru.mail.zelenskaya.app.service.convertor.ConvertService;
import ru.mail.zelenskaya.app.service.model.StudentDTO;

import java.util.List;

@Service
@AllArgsConstructor
public class SelectStudentsServiceImpl implements SelectStudentsService {
    private final StudentRepository studentRepository;
    private final ConvertService convertService;

    @Override
    @Transactional
    public List<StudentDTO> getAll() {
        List<Student> students = studentRepository.findAll();
        return convertService.convertToListDTO(students);
    }
}
