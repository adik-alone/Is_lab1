package ru.is_lab1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class OwnedEntity extends AbstractEntity {
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;
}
