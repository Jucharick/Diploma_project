package ru.jucharick.TasksAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jucharick.TasksAPI.domain.Phone;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью Phone.
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    /**
     * Получение по номеру телефона
     */
    Optional<Phone> findByPhoneNumber(String phoneNumber);

    /**
     * Удаление номера телефона.
     */
    void deleteByPhoneNumber(String phoneNumber);
}
