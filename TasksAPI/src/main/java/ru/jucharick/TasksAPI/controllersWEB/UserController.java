package ru.jucharick.TasksAPI.controllersWEB;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ru.jucharick.TasksAPI.domain.Task;;
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
    public String createUserForm(User user, Model model){
        List<Team> teams = userService.findAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user){
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
        List<Team> teams = userService.findAllTeams();
        model.addAttribute("teams", teams);
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
