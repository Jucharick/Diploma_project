package ru.jucharick.TasksAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.jucharick.TasksAPI.domain.Team;

@EnableJpaRepositories
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}