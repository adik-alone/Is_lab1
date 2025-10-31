package ru.is_lab1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ms_coordinates")
@Data
public class Coordinates extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="x")
    private float x;

    @Column(name="y", nullable = false)
    private Double y;
}
