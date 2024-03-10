package ru.jucharick.TasksAPI.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность номера телефона.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "phones")
public class Phone {
    //region Поля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long phoneId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    //endregion

    //region Методы
    @Override
    public String toString() {
        return phoneNumber;
    }
    //endregion
}
