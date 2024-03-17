package ru.jucharick.TasksAPI.services.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import ru.jucharick.TasksAPI.domain.Team;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.exception.UserNotFoundException;
import ru.jucharick.TasksAPI.repositories.TeamRepository;
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
    private final TeamRepository teamRepository;

    //endregion

    //region Методы
    /**
     * Получение всех пользователей.
     */
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Получение пользователя по id.
     */
    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("User by " + id + " not found!"));
    }

    /**
     * Создание пользователя.
     */
    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя по id.
     */
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Обновление пользователя по id.
     */
    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPatronymic(user.getPatronymic());

            if (user.getBirthday() != null) {
                existingUser.setBirthday(user.getBirthday());
            }

            if (user.getPosition() != null) {
                existingUser.setPosition(user.getPosition());
            }

            if (user.getTeam() != null) {
                existingUser.setTeam(user.getTeam());
            }

            existingUser.setPhone(user.getPhone());

            existingUser.setEmail(user.getEmail());

            return userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("User not found");
        }


    }

    /**
     * Получение всех команд.
     */
    @Override
    public List<Team> findAllTeams(){
        return teamRepository.findAll();
    }

    /**
     * Поиск команды по ID.
     */
    @Override
    public Team getTeamById(Long id){
        return teamRepository.findById(id).orElse(null);
    }
    //endregion
}