package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ms_person")
public class Person extends AbstractEntity{
    @Column(name = "name", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Column(name = "eyeColor", nullable = false)
    private Color eyeColor; //Поле не может быть null

    @Column(name = "hairColor", nullable = false)
    private Color hairColor; //Поле не может быть null

    @Column(name = "location", nullable = true)
    private Location location; //Поле может быть null

    @Column(name = "height")
    private int height; //Значение поля должно быть больше 0

    @Column(name = "weight", nullable = false)
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0

    @Column(name = "passportID", nullable = false)
    private String passportID; //Поле не может быть null

    @Column(name = "nationality", nullable = false)
    private Country nationality; //Поле не может быть null
}
