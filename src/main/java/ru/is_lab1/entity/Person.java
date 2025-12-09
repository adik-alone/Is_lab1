package ru.is_lab1.entity;

import jakarta.json.bind.annotation.JsonbTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import ru.is_lab1.entity.enums.Color;
import ru.is_lab1.entity.enums.Country;

import java.io.Serializable;

@Entity
@Table(name = "ms_person")
@Data
public class Person extends AbstractEntity implements Serializable {
    @Column(name = "name", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Enumerated(EnumType.STRING)
    @Column(name = "eyeColor", nullable = false)
    private Color eyeColor; //Поле не может быть null

    @Enumerated(EnumType.STRING)
    @Column(name = "hairColor", nullable = false)
    private Color hairColor; //Поле не может быть null

//    @Column(name = "location", nullable = true)
//    @JoinColumn(name = "location_id")
    @ManyToOne
    @JoinColumn(
            name = "location_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_person_location",
                    foreignKeyDefinition = "FOREIGN KEY (location_id) REFERENCES ms_location(id) ON DELETE CASCADE"
            )
    )
    private Location location; //Поле может быть null

    @Column(name = "height")
    private int height; //Значение поля должно быть больше 0

    @Column(name = "weight", nullable = false)
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0

    @Column(name = "passportID", nullable = false)
    private String passportID; //Поле не может быть null

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", nullable = false)
    private Country nationality; //Поле не может быть null
}
