package ru.is_lab1.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesRequest {
    @Min(value = -781, message = "X must be more than -781")
    private float x;
    @NotNull(message = "Y cannot be null")
    private Double y;
}
