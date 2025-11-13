package ru.is_lab1.exceptions;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private String message;
    private String timestamp;

    public ExceptionResponse(String message){
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
}
