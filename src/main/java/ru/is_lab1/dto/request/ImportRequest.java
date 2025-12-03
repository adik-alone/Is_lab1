package ru.is_lab1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ImportRequest {
    private Boolean isSuccess;
    private Long count;
    private String url;
}
