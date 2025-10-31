package ru.is_lab1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import ru.is_lab1.entity.enums.MovieGenre;
import ru.is_lab1.entity.enums.MpaaRating;

@Entity
@Table(name = "ms_movie")
@Data
public class Movie extends AbstractEntity{

    @Column(name = "name", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

//    @Column(name = "coordinates", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "coordinates_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_ms_movie_coordinates_id",
                    foreignKeyDefinition = "FOREIGN KEY (coordinates_id) REFERENCES ms_coordinates(id) ON DELETE CASCADE"
            )
    )
//    @CascadeOnDelete
    private Coordinates coordinates; //Поле не может быть null

    @Column(name = "creationDate", nullable = false)
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "oscarsCount")
    private long oscarsCount; //Значение поля должно быть больше 0

    @Column(name = "budget")
    private double budget; //Значение поля должно быть больше 0

    @Column(name = "totalBoxOffice")
    private float totalBoxOffice; //Значение поля должно быть больше 0

    @Enumerated(EnumType.STRING)
    @Column(name = "mpaaRating", nullable = false)
    private MpaaRating mpaaRating; //Поле не может быть null

    @ManyToOne(fetch = FetchType.EAGER)
//    @Column(name = "director", nullable = false)
    @JoinColumn(
            name = "director_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_ms_movie_director_id",
                    foreignKeyDefinition = "FOREIGN KEY (director_id) REFERENCES ms_person(id) ON DELETE CASCADE"
            )
    )
//    @CascadeOnDelete
    private Person director; //Поле не может быть null

//    @Column(name = "screenwriter")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "screenwriter_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                name = "fk_ms_movie_screenwriter_id",
                foreignKeyDefinition = "FOREIGN KEY (screenwriter_id) REFERENCES ms_person(id) ON DELETE CASCADE"
            )
    )
    @CascadeOnDelete
    private Person screenwriter;

//    @Column(name = "operator", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "operator_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_ms_movie_operator_id",
                    foreignKeyDefinition = "FOREIGN KEY (operator_id) REFERENCES ms_person(id) ON DELETE CASCADE"
            )
    )
    private Person operator; //Поле не может быть null

    @Column(name = "length")
    private long length; //Значение поля должно быть больше 0

    @Column(name = "goldenPalmCount")
    private int goldenPalmCount; //Значение поля должно быть больше 0

    @Column(name = "usaBoxOffice")
    private float usaBoxOffice; //Значение поля должно быть больше 0

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private MovieGenre genre; //Поле не может быть null
}
