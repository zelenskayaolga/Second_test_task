package ru.mail.zelenskaya.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mail.zelenskaya.app.repository.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    int deleteStudentById(Long id);
}
