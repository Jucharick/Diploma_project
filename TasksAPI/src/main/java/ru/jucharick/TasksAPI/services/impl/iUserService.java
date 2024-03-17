package ru.jucharick.TasksAPI.services.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import ru.jucharick.TasksAPI.domain.Team;
import ru.jucharick.TasksAPI.domain.User;
import ru.jucharick.TasksAPI.exception.TaskNotFoundException;
import ru.jucharick.TasksAPI.exception.UserNotFoundException;
import ru.jucharick.TasksAPI.exception.UserUpdateException;
import ru.jucharick.TasksAPI.repositories.EmailRepository;
import ru.jucharick.TasksAPI.repositories.PhoneRepository;
import ru.jucharick.TasksAPI.repositories.TeamRepository;
import ru.jucharick.TasksAPI.repositories.UserRepository;
import ru.jucharick.TasksAPI.services.UserServiceApi;

import ru.jucharick.TasksAPI.domain.Phone;
import ru.jucharick.TasksAPI.domain.Email;

@Service
@AllArgsConstructor
public class iUserService implements UserServiceApi {
    //region Поля
    /**
     * UserRepository
     */
    private final UserRepository userRepository;

    /**
     * TeamRepository
     */
    private final TeamRepository teamRepository;

    /**
     * PhoneRepository
     */
    private final PhoneRepository phoneRepository;

    /**
     * EmailRepository
     */
    private final EmailRepository emailRepository;

    /**
     * Проверка телефона.
     */
    private static final String PHONE_REGEX = "^\\+7-(\\d{3}-){2}\\d{2}-\\d{2}$";

    /**
     * Проверка email.
     */
    private static final String EMAIL_REGEX ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

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
    public void updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPatronymic(user.getPatronymic());
            if (user.getBirthday() != null) {
                existingUser.setBirthday(user.getBirthday());
            }
            existingUser.setPosition(user.getPosition());
            existingUser.setTeam(user.getTeam());

            List<Phone> existingPhones = existingUser.getPhones();
            for (Phone phone : user.getPhones()) {
                if (!existingPhones.contains(phone)) {
                    existingPhones.add(phone);
                }
            }
            existingUser.setPhones(existingPhones);

            List<Email> existingEmails = existingUser.getEmails();
            for (Email email : user.getEmails()) {
                if (!existingEmails.contains(email)) {
                    existingEmails.add(email);
                }
            }
            existingUser.setEmails(existingEmails);
            userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Добавление нового телефона пользователю.
     */
    @Override
    public void addPhone(User updateUser, String phone) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new UserUpdateException("Invalid phone format");
        }
        checkPhone(phone);
        Phone newPhone = new Phone();
        newPhone.setPhoneNumber(phone);
        newPhone.setUser(updateUser);
        updateUser.getPhones().add(newPhone);
    };

    /**
     * Добавление нового email пользователю.
     */
    @Override
    public void addEmail(User updateUser, String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new UserUpdateException("Invalid email format");
        }
        checkEmail(email);
        Email newEmail = new Email();
        newEmail.setEmailAddress(email);
        newEmail.setUser(updateUser);
        updateUser.getEmails().add(newEmail);
    };

    /**
     * Удаление номера телефона у пользователя.
     */
    @Override
    public User removePhone(Long userId, String phone) {
        User user = getUserById(userId);
        boolean phoneExists = user.getPhones()
                .stream()
                .anyMatch(p -> p.getPhoneNumber().equals(phone));

        if (!phoneExists){
            throw new UserUpdateException("Phone " + phone + " not found!");
        }

        if (user.getPhones().size() == 1){
            throw new UserUpdateException("You can't delete the last number!");
        }
        user.getPhones().removeIf(p -> p.getPhoneNumber().equals(phone));
        phoneRepository.deleteByPhoneNumber(phone);

        return userRepository.save(user);
    };

    /**
     * Удаление email у пользователя.
     */
    @Override
    public User removeEmail(Long userId, String email) {
        User user = getUserById(userId);
        boolean emailExists = user.getEmails()
                .stream()
                .anyMatch(e -> e.getEmailAddress().equals(email));

        if (!emailExists){
            throw new UserUpdateException("Email address" + email + " not found.");
        }

        if (user.getEmails().size() == 1){
            throw new UserUpdateException("You can't delete the last email address.");
        }
        user.getEmails().removeIf(e -> e.getEmailAddress().equals(email));
        emailRepository.deleteByEmailAddress(email);

        return userRepository.save(user);
    };

    /**
     * Проверка существования номера телефона в БД.
     */
    private void checkPhone(String phone) throws UserUpdateException {
        Optional<Phone> findPhone = phoneRepository.findByPhoneNumber(phone);
        if (findPhone.isPresent()){
            throw new UserUpdateException("Phone " + findPhone.get().getPhoneNumber() + " is busy!");
        }
    }

    /**
     * Проверка существования email в БД.
     */
    private void checkEmail(String email) throws UserUpdateException{
        Optional<Email> findEmail = emailRepository.findByEmailAddress(email);
        if (findEmail.isPresent()){
            throw new UserUpdateException("Email " + findEmail.get().getEmailAddress() + " is busy!");
        }
    }

    //endregion
}