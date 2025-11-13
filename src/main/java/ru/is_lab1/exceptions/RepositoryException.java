package ru.is_lab1.exceptions;

public class RepositoryException extends RuntimeException {
    public RepositoryException (String message){
        super(message);
    }
}
