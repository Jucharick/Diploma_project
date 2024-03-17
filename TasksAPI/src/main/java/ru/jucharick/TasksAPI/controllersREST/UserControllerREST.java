package ru.jucharick.TasksAPI.controllersREST;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.services.FileGateway;
import ru.jucharick.TasksAPI.services.UserServiceApi;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST запросы для сущности User
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/")
public class UserControllerREST {
    //region Поля
    /**
     * TaskService
     */
    private final UserServiceApi userServiceApi;
    private final FileGateway fileGateway;
    //endregion

    //region Методы
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userServiceApi.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User UserById;
        try {
            UserById = userServiceApi.getUserById(id);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new User());
        }
        return new ResponseEntity<>(UserById, HttpStatus.OK);
    }

    @PostMapping("/users/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        fileGateway.writeToFile(user.getLastName() + ".txt", user.toString());
        fileGateway.writeLog("log_user.txt", LocalDateTime.now() + "  вызван метод createUser() REST " + "создан User " + user.getLastName());
        return new ResponseEntity<>(userServiceApi.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping ("/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        fileGateway.writeLog("log_user.txt", LocalDateTime.now() + "  вызван метод deleteUser() REST " + "удален User id " + id);
        userServiceApi.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Изменение задачи.
     */
    @PutMapping("/users/update/{id}")
    public ResponseEntity<User> updateTask(@PathVariable Long id, @RequestBody User user) {
        fileGateway.writeLog("log_user.txt", LocalDateTime.now() + "  вызван метод updateUser() REST " + "изменена таска id " + user.getId()+ " " + user.getLastName());
        return new ResponseEntity<>(userServiceApi.updateUser(id, user), HttpStatus.OK);
    }
    //endregion
}

