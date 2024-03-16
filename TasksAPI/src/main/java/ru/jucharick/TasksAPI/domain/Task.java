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
    private Long task_id;

    /**
     * Заголовок задачи
     */
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    /**
     * Дата создания задачи
     */
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDate;

    /**
     * дата изменения задачи
     */
    @Column(name = "update_date_time", nullable = false)
    private LocalDateTime updateDate;

    /**
     * Срок выполнения задачи
     */
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    /**
     * Описание задачи
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * Статус задачи:
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
     * Кто запросил задачу (ID of the user)
     */
    @ManyToOne
    @JoinColumn(name = "requested_by")
    private User requestedBy;

    /**
     * Кто назначил задачу (ID of the user)
     */
    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private User assignedBy;

    /**
     * Исполнитель (ID of the user)
     */
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assigneeID;
    //endregion
}

