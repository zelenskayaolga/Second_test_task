package ru.mail.zelenskaya.app.service;

import ru.mail.zelenskaya.app.service.model.StudentDTO;

import java.util.List;

public interface SelectStudentsService {
    List<StudentDTO> getAll();
}
