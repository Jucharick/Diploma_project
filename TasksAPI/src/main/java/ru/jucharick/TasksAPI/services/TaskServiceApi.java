package ru.jucharick.TasksAPI.services;

import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.User;

import java.util.List;

public interface TaskServiceApi {
    List<Task> findAll();
    Task getTaskById(Integer id);
    Task saveTask(Task task);
    void deleteById(Integer id);
    void updateTask(Integer id, Task task);
    List<Task> findTaskByUserId(User user);
}
