package ru.is_lab1.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.dto.request.PersonRequest;
import ru.is_lab1.entity.Person;
import ru.is_lab1.service.PersonService;

import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {
    @Inject
    PersonService service;

    @POST
    public Response createPerson(@Valid PersonRequest request){
        Person person = service.createPerson(request);
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id){
        Person person = service.getPersonById(id);
        return Response.ok(person).build();
    }

    @GET
    @Path("/page-{page}-by-{size}")
    public Response getPagePerson(@PathParam("page") int page,
                                  @PathParam("size") int size){
        List<Person> people = service.getPagePersons(page, size);
        return Response.ok(people).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") Long id,
                                 @Valid PersonRequest request){
        Person updatedPerson = service.updatePerson(id, request);
        return Response.ok(updatedPerson).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id){
        service.deletePerson(id);
        return Response.noContent().build();
    }
}
