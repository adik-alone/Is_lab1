package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ms_coordinates")
public class Coordinates extends AbstractEntity{
    @Column(name="x")
    private float x;

    @Column(name="y", nullable = false)
    private Double y;
}
