package ru.jucharick.TasksAPI.services;

import ru.jucharick.TasksAPI.domain.User;

import java.util.List;

public interface UserServiceApi {
    List<User> findAll();
    User getUserById(Integer id);
    User saveUser(User user);
    void deleteById(Integer id);
    void updateUser(Integer id, User user);
}
