package ru.jucharick.TasksAPI.controllersWEB;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.Team;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.services.TaskServiceApi;
import ru.jucharick.TasksAPI.services.UserServiceApi;

@Controller
@AllArgsConstructor
public class UserController {
    //region Поля
    /**
     * UserService
     */
    private final UserServiceApi userService;
    private final TaskServiceApi taskService;
    //endregion

    //region Методы
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping ("/user/{id}")
    public String findUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Task> tasks = taskService.findTaskByUserId(user);
        model.addAttribute("tasks", tasks);
        return "user";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping ("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user.getId(), user);
        return "redirect:/users";
    }
    //endregion
}
