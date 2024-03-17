package ru.jucharick.TasksAPI.services;

import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.User;

import java.util.List;

public interface TaskServiceApi {
    /**
     * Получение всех задач.
     */
    List<Task> findAll();

    /**
     * Получение задачи по id.
     */
    Task getTaskById(Long id);

    /**
     * Создание задачи.
     */
    Task createTask(Task task);

    /**
     * Удаление задачи по id.
     */
    void deleteById(Long id);

    /**
     * Обновление задачи по id.
     */
    void updateTask(Long id, Task task);

    /**
     * Получение всех задач по UserId.
     */
    List<Task> findTaskByUserId(User user);
}
