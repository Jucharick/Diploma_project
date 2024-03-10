package ru.jucharick.TasksAPI.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Члены команды.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "teamMembers")
public class TeamMember {

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
