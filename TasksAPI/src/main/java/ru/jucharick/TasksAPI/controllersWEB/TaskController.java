package ru.jucharick.TasksAPI.controllersWEB;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.services.FileGateway;
import ru.jucharick.TasksAPI.services.TaskServiceApi;

@Controller
@AllArgsConstructor
public class TaskController {
    //region Поля
    /**
     * TaskService
     */
    private final TaskServiceApi taskService;
    private final FileGateway fileGateway;
    //endregion

    //region Методы
    @GetMapping("/tasks")
    public String findAllTask(Model model){
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks-list";
    }

    @GetMapping("/task-view/{id}")
    public String findAllTask(@PathVariable("id") Long id, Model model){;
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-view";
    }

    @GetMapping("/task-create")
    public String creatTaskForm(Task task){
        return "task-create";
    }

    @PostMapping("/task-create")
    public String createTask(Task task){
        fileGateway.writeToFile(task.getTitle() + ".txt", task.toString());
        fileGateway.writeLog("log.txt", LocalDateTime.now() + "  вызван метод createTask() WEB " + "создана таска " + task.getTitle());
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping ("/task-delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        fileGateway.writeLog("log.txt", LocalDateTime.now() + "  вызван метод deleteTask() WEB " + "удалена таска id " + id);
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

    /**
     * Изменение задачи.
     * @return представление для изменения данных.
     */
    @GetMapping("/task-update/{id}")
    public String getTaskUpdateForm(@PathVariable("id") Long id, Model model){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        fileGateway.writeLog("log.txt", LocalDateTime.now() + "  получен WEB запрос на изменение задачи id " + task.getTask_id() + " " + task.getTitle());
        return "task-update";
    }

    /**
     * Получение данных об измененном задачи с формы представления.
     * @return перенаправление на страницу со списком пользователей.
     */
    @PostMapping("/task-update")
    public String postTaskUpdateForm(@ModelAttribute Task task){
        taskService.updateTask(task.getTask_id(), task);
        fileGateway.writeLog("log.txt", LocalDateTime.now() + "  вызван метод updateTask() WEB " + "изменена таска id " + task.getTask_id() + " " + task.getTitle());
       return "redirect:/tasks";
    }
    //endregion
}
