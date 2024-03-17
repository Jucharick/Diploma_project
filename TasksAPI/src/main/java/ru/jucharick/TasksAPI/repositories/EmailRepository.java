package ru.jucharick.TasksAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jucharick.TasksAPI.domain.Email;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью email.
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    /**
     * Получение объекта по email.
     */
    Optional<Email> findByEmailAddress(String emailAddress);

    /**
     * Удаление email.
     */
    void deleteByEmailAddress(String emailAddress);
}
