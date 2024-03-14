package ru.jucharick.TasksAPI.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.exception.TaskNotFoundException;
import ru.jucharick.TasksAPI.repositories.TaskRepository;
import ru.jucharick.TasksAPI.services.TaskServiceApi;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class iTaskService implements TaskServiceApi {
    //region Поля
    /**
     * TaskRepository
     */
    private final TaskRepository taskRepository;

    //endregion

    //region

    /**
     * поиск задач по исполнителю
     */
    @Override
    public List<Task> findTaskByUserId(User user){
        return taskRepository.findByAssigneeID(user);
    }

    @Override
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Integer id){
        return taskRepository.findById(id).orElseThrow(()
                -> new TaskNotFoundException());
    }

    @Override
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(Integer id, Task task) {
        Task taskById = getTaskById(id);
        taskById.setTitle(task.getTitle());
        taskById.setCreateDate(task.getCreateDate());
        taskById.setUpdateDate(LocalDateTime.now());
        taskById.setDeadline(taskById.getDeadline());
        taskById.setDescription(task.getDescription());
        taskById.setStatus(task.getStatus());
        taskById.setRequestedBy(task.getRequestedBy());
        taskById.setAssignedBy(task.getAssignedBy());
        taskById.setAssigneeID(task.getAssigneeID());
        taskRepository.save(taskById);
    }
    //endregion
}
