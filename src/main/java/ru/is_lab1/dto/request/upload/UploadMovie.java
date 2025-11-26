package ru.is_lab1.dto.request.upload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.is_lab1.dto.base.MovieDTO;
import ru.is_lab1.entity.enums.MovieGenre;
import ru.is_lab1.entity.enums.MpaaRating;

@Data
public class UploadMovie extends MovieDTO {
    @NotNull(message = "Name couldn't be null")
    @NotEmpty(message = "Name couldn't be empty")
    private String name;

    @NotNull(message = "Coordinates couldn't be null")
    @Valid
    private UploadCoordinates coordinates;

    @Min(value = 0, message = "Oscars must be more than 0")
    private long oscarsCount;

    @Min(value = 100, message = "Budget must be more than 100")
    private double budget;

    @Min(value = 0, message = "Total Box Office must be more than 0")
    private float totalBoxOffice;

    @NotNull(message = "Rating couldn't be null")
    private MpaaRating mpaaRating;

    @NotNull(message = "Director couldn't be null")
    @Valid
    private UploadPerson director;

    @Valid
    private UploadPerson screenwriter;

    @NotNull(message = "Operator couldn't be null")
    @Valid
    private UploadPerson operator;

    @Min(value = 0, message = "Length must be more than 0")
    @Max(value = 600, message = "Length must be less than 600")
    private long length;

    @Min(value = 0, message = "Golden Palm must be more than 0")
    private int goldenPalmCount;

    @Min(value = 0, message = "USA Box Office must be more than 0")
    private float usaBoxOffice;

    @NotNull(message = "Genre couldn't be null")
    private MovieGenre genre;
}
