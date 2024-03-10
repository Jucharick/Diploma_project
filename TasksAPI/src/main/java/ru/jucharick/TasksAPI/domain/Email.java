package ru.jucharick.TasksAPI.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность электронной почты.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "emails")
public class Email {
    //region Поля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private int emailId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "email", unique = true, nullable = false)
    private String emailAddress;

    //endregion

    //region Методы
    @Override
    public String toString() {
        return emailAddress;
    }
    //endregion
}
