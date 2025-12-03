package ru.is_lab1.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.is_lab1.exceptions.exception.UserNotFoundException;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    @Override
    public Response toResponse(UserNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());

        return Response.status(Response.Status.UNAUTHORIZED).entity(exceptionResponse).build();
    }
}
