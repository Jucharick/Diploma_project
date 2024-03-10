package ru.jucharick.TasksAPI.services.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.exception.UserNotFoundException;
import ru.jucharick.TasksAPI.repositories.UserRepository;
import ru.jucharick.TasksAPI.services.UserServiceApi;

@Service
@AllArgsConstructor
public class iUserService implements UserServiceApi {
    //region Поля
    /**
     * UserRepository
     */
    private final UserRepository userRepository;
    //endregion

    //region Методы
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException());
    }

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Integer id, User user) {
        User userById = getUserById(id);
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        userById.setPatronymic(user.getPatronymic());
        userById.setBirthday(user.getBirthday());
        userById.setPosition(user.getPosition());
        userById.setTeam(user.getTeam());
        userById.setEmails(user.getEmails());
        userById.setPhones(user.getPhones());
        userRepository.save(userById);
    }
    //endregion
}