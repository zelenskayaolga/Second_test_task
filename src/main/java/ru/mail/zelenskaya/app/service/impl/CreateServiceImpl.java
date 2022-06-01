package ru.mail.zelenskaya.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.zelenskaya.app.repository.StudentRepository;
import ru.mail.zelenskaya.app.repository.model.Student;
import ru.mail.zelenskaya.app.service.CreateService;
import ru.mail.zelenskaya.app.service.convertor.ConvertService;
import ru.mail.zelenskaya.app.service.model.StudentDTO;

@Service
@AllArgsConstructor
public class CreateServiceImpl implements CreateService {
    private final StudentRepository studentRepository;
    private final ConvertService convertService;

    @Override
    @Transactional
    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = convertService.convertToEntity(studentDTO);
        Student addedStudent = studentRepository.save(student);
        return convertService.convertToDto(addedStudent);
    }
}