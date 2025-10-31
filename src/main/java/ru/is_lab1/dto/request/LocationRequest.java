package ru.is_lab1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequest {
    private long x;

    private float y;

    private int z;
}
