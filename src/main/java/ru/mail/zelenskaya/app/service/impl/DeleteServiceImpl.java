package ru.mail.zelenskaya.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.zelenskaya.app.repository.StudentRepository;
import ru.mail.zelenskaya.app.service.DeleteService;

@Service
@AllArgsConstructor
public class DeleteServiceImpl implements DeleteService {
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public int deleteStudentById(Long id) {
        return studentRepository.deleteStudentById(id);
    }
}
