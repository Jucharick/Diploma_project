package ru.jucharick.TasksAPI.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.exception.TaskNotFoundException;
import ru.jucharick.TasksAPI.repositories.TaskRepository;
import ru.jucharick.TasksAPI.services.TaskServiceApi;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class iTaskService implements TaskServiceApi {
    //region Поля
    /**
     * TaskRepository
     */
    private final TaskRepository taskRepository;
    //endregion

    //region
    /**
     * Получение всех задач.
     */
    @Override
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    /**
     * Получение задачи по id.
     */
    @Override
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()
                -> new TaskNotFoundException("Task by " + id + " not found!"));
    }

    /**
     * Создание задачи.
     */
    @Override
    public Task createTask(Task task){
        task.setCreateDate(LocalDateTime.now());
        task.setUpdateDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * Удаление задачи по id.
     */
    @Override
    public void deleteById(Long id) {
        Task taskById = getTaskById(id);
        taskRepository.delete(taskById);
    }

    /**
     * Обновление задачи по id.
     */
    @Override
    @Transactional
    public Task updateTask(Long id, Task task) {
        Task taskById = getTaskById(id);
        if (taskById != null) {
            taskById.setTitle(task.getTitle());
            taskById.setUpdateDate(LocalDateTime.now());
            if (task.getDeadline() != null) {
                taskById.setDeadline(task.getDeadline());
            }
            taskById.setDescription(task.getDescription());
            taskById.setStatus(task.getStatus());
            taskById.setRequestedBy(task.getRequestedBy());
            taskById.setAssignedBy(task.getAssignedBy());
            taskById.setAssigneeID(task.getAssigneeID());
            return taskRepository.save(taskById);
        } else {
            throw new TaskNotFoundException("Task not found");
        }
    }

    /**
     * Получение всех задач по UserId.
     */
    @Override
    public List<Task> findTaskByUserId(User user){
        return taskRepository.findByAssigneeID(user);
    }
    //endregion
}
