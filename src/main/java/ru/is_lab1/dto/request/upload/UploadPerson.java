package ru.is_lab1.dto.request.upload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import ru.is_lab1.entity.enums.Color;
import ru.is_lab1.entity.enums.Country;

@Data
public class UploadPerson {
    @NotNull(message = "Name couldn't be null")
    @NotEmpty(message = "Name couldn't be empty")
    private String name;

    @NotNull(message = "eyeColor couldn't be null")
    private Color eyeColor;

    @NotNull(message = "hairColor couldn't be null")
    private Color hairColor;

    @Valid
    private UploadLocation location;

    @Min(value = 0, message = "Height must be more than 0")
    @Max(value = 300, message = "Height must be less than 300")
    private int height;

    @NotNull(message = "Weight couldn't be null")
    @Min(value = 0, message = "Weight must be more than 0")
    @Max(value = 200, message = "Weight must be less than 200")
    private Long weight;

    @NotNull(message = "PassportID couldn't be null")
    @Pattern(regexp = "\\d{3}:\\d{4}:[A-Z]{2,}")
    private String passportID;

    @NotNull(message = "Nationality couldn't be null")
    private Country nationality;
}
