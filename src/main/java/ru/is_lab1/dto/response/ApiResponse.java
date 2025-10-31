package ru.is_lab1.dto.response;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String timestamp;
}
