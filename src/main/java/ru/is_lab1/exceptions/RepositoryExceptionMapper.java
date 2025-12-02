package ru.is_lab1.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.is_lab1.exceptions.exception.RepositoryException;

@Provider
public class RepositoryExceptionMapper implements ExceptionMapper<RepositoryException> {
    @Override
    public Response toResponse(RepositoryException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).build();
    }
}
