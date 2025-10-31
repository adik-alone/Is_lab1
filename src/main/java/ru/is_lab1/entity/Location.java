package ru.is_lab1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="ms_location")
@Data
public class Location extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="x")
    private long x;

    @Column(name="y")
    private float y;

    @Column(name="z")
    private int z;
}
