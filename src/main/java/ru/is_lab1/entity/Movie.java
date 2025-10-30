package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ms_movie")
public class Movie extends AbstractEntity{
    @Column(name = "name", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Column(name = "coordinates", nullable = false)
    private Coordinates coordinates; //Поле не может быть null

    @Column(name = "creationDate", nullable = false)
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "oscarsCount")
    private long oscarsCount; //Значение поля должно быть больше 0

    @Column(name = "budget")
    private double budget; //Значение поля должно быть больше 0

    @Column(name = "totalBoxOffice")
    private float totalBoxOffice; //Значение поля должно быть больше 0

    @Column(name = "mpaaRating", nullable = false)
    private MpaaRating mpaaRating; //Поле не может быть null

    @Column(name = "director", nullable = false)
    private Person director; //Поле не может быть null

    @Column(name = "screenwriter")
    private Person screenwriter;

    @Column(name = "operator", nullable = false)
    private Person operator; //Поле не может быть null

    @Column(name = "length")
    private long length; //Значение поля должно быть больше 0

    @Column(name = "goldenPalmCount")
    private int goldenPalmCount; //Значение поля должно быть больше 0

    @Column(name = "usaBoxOffice")
    private float usaBoxOffice; //Значение поля должно быть больше 0

    @Column(name = "genre", nullable = false)
    private MovieGenre genre; //Поле не может быть null
}
