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
    User updateUser(Long id, User user);

    /**
     * Получение всех команд.
     */
    List<Team> findAllTeams();

    Team getTeamById(Long id);
}
