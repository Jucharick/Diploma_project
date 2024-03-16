package ru.jucharick.TasksAPI.services;

import ru.jucharick.TasksAPI.domain.Team;
import ru.jucharick.TasksAPI.domain.User;

import java.util.List;

public interface UserServiceApi {
    /**
     * Получение всех пользователей.
     */
    List<User> findAll();

    /**
     * Получение пользователя по id.
     */
    User getUserById(Long id);

    /**
     * Создание пользователя.
     */
    User createUser(User user);

    /**
     * Удаление пользователя по id.
     */
    void deleteById(Long id);

    /**
     * Обновление пользователя по id.
     */
    void updateUser(Long id, User user);

    /**
     * Добавление нового телефона пользователю.
     */
    void addPhone(User updateUser, String phone);

    /**
     * Добавление нового email пользователю.
     */
    void addEmail(User updateUser, String email);

    /**
     * Удаление номера телефона у пользователя.
     */
    User removePhone(Long userId, String phone);

    /**
     * Удаление email у пользователя.
     */
    User removeEmail(Long userId, String email);
}
