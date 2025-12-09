package ru.is_lab1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "ms_coordinates")
@Data
public class Coordinates extends AbstractEntity implements Serializable {
    @Column(name="x")
    private float x;

    @Column(name="y", nullable = false)
    private Double y;
}
