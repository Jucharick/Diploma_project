package ru.jucharick.TasksAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jucharick.TasksAPI.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
