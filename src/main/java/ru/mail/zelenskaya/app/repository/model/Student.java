package ru.mail.zelenskaya.app.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    @Column(name = "birth")
    private LocalDate dateOfBirth;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getSurname(), student.getSurname()) &&
                Objects.equals(getName(), student.getName()) &&
                Objects.equals(getPatronymic(), student.getPatronymic()) &&
                Objects.equals(getDateOfBirth(), student.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSurname(), getName(), getPatronymic(), getDateOfBirth());
    }
}