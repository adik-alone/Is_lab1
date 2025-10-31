package ru.is_lab1.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.is_lab1.entity.Location;
import ru.is_lab1.entity.enums.Color;
import ru.is_lab1.entity.enums.Country;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    @NotNull(message = "Name couldn't be null")
    @NotEmpty(message = "Name couldn't be empty")
    private String name;

    @NotNull(message = "eyeColor couldn't be null")
    private Color eyeColor;

    @NotNull(message = "hairColor couldn't be null")
    private Color hairColor;

    private Long location;

    @Min(value = 0, message = "Height must be more than 0")
    private int height;

    @NotNull(message = "Weight couldn't be null")
    @Min(value = 0, message = "Weight must be more than 0")
    private Long weight;

    @NotNull(message = "PassportID couldn't be null")
    private String passportID;

    @NotNull(message = "Nationality couldn't be null")
    private Country nationality;
}
