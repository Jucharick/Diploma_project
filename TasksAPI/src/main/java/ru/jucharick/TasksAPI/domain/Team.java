package ru.jucharick.TasksAPI.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность команды.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "teams")
public class Team {
    //region Поля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_title", nullable = false, length = 50)
    private String teamTitle;

    @Column(name = "team_description", nullable = false, length = 100)
    private String teamDescription;
    //endregion
}
