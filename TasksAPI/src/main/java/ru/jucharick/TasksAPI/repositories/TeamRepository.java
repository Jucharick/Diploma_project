package ru.jucharick.TasksAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jucharick.TasksAPI.domain.Team;

/**
 * Репозиторий для работы с сущностью Team.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
