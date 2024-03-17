package ru.jucharick.TasksAPI.controllersREST;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.services.FileGateway;
import ru.jucharick.TasksAPI.services.TaskServiceApi;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST запросы для для сущности Task
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/")
public class TaskControllerREST {
    //region Поля
    /**
     * TaskService
     */
    private final TaskServiceApi taskService;
    private final FileGateway fileGateway;
    //endregion

    //region Методы
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        Task TaskById;
        try {
            TaskById = taskService.getTaskById(id);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Task());
        }
        return new ResponseEntity<>(TaskById, HttpStatus.OK);
    }

    @PostMapping("/tasks/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        fileGateway.writeToFile(task.getTitle() + ".txt", task.toString());
        fileGateway.writeLog("log.txt", LocalDateTime.now() + "  вызван метод createTask() REST " + "создана таска " + task.getTitle());
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @DeleteMapping ("/tasks/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        fileGateway.writeLog("log.txt", LocalDateTime.now() + "  вызван метод deleteTask() REST " + "удалена таска id " + id);
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Изменение задачи.
     */
    @PutMapping("/tasks/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        fileGateway.writeLog("log.txt", LocalDateTime.now() + "  вызван метод updateTask() REST " + "изменена таска id " + task.getTask_id() + " " + task.getTitle());
        return new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }
    //endregion
}
