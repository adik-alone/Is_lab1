package ru.is_lab1.exceptions;

import ru.is_lab1.exceptions.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());

        return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponse).build();
    }
}
