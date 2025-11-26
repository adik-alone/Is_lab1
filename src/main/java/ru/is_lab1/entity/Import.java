package ru.is_lab1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ms_import")
public class Import extends OwnedEntity {
    @Column(name = "is_success")
    private Boolean isSuccess;
    @Column(name = "count")
    private Long count;
}
