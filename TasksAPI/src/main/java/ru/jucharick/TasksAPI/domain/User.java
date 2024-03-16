package ru.jucharick.TasksAPI.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    //region Поля
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    /**
     * Имя
     */
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName ;

    /**
     * Фамилия
     */
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName ;

    /**
     * Отчество
     */
    @Column(name = "patronymic", nullable = false, length = 50)
    private String patronymic ;

    /**
     * Дата рождения
     */
    @Column(name = "birthday", nullable = false)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    /**
     * Должность
     */
    @Column(name = "position", nullable = false, length = 50)
    private String position;

    /**
     * id Команды
     */
    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    /**
     * email
     */
    @OneToMany(mappedBy = "user")
    private List<Email> emails;

    /**
     * List<Phone>
     */
    @OneToMany(mappedBy = "user")
    private List<Phone> phones;
    //endregion
}
