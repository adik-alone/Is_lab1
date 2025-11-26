package ru.is_lab1.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.is_lab1.entity.enums.Role;

@Entity
@Data
@Table(name = "ms_user")
public class User extends AbstractEntity {
    @Id
    @GeneratedValue
    Long id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
