package ru.jucharick.TasksAPI.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class Task {
    //region Поля
    /**
     * ID задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int task_id;

    /**
     * заголовок задачи
     */
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    /**
     * дата создания задачи
     */
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDate;

    /**
     * дата изменения задачи
     */
    @Column(name = "update_date_time", nullable = false)
    private LocalDateTime updateDate;

    /**
     * описание задачи
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * статус задачи:
     *   assigned
     *   in_progress
     *   resolved
     *   reopened
     *   closed
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    /**
     * кто запросил задачу (ID of the user)
     */
    @ManyToOne
    @JoinColumn(name = "requested_by")
    private User requestedBy;

    /**
     * кто назначил задачу (ID of the user)
     */
    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private User assignedBy;

    /**
     * исполнитель (ID of the user)
     */
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assigneeID;
    //endregion
}

