package ru.jucharick.TasksAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.jucharick.TasksAPI.domain.Task;
import ru.jucharick.TasksAPI.domain.User;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Поиск задач по id исполнителя
     */
    List<Task> findByAssigneeID(User user);
}
