package ru.mail.zelenskaya.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mail.zelenskaya.app.repository.model.Group;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findGroupByGroupNumber(Integer groupNumber);

    boolean existsGroupByGroupNumber(Integer groupNumber);
}
