package ru.is_lab1.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.dto.request.UserRequest;
import ru.is_lab1.entity.User;
import ru.is_lab1.service.UserService;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UserService service;

    @POST
    public Response createUser(@Valid UserRequest request){
        User user = service.createUser(request);
        return Response.ok(user).build();
    }

    @POST
    @Path("/auth")
    public Response auth(@Valid UserRequest request){
        boolean auth = service.isUserValid(request);
        return auth ? Response.noContent().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id){
        User user = service.getUserById(id);
        return Response.ok(user).build();
    }

    @GET
    @Path("/page-{page}-by-{size}")
    public Response getPageUser(@PathParam("page") int page,
                                @PathParam("size") int size){
        List<User> users = service.getPageUser(page, size);
        return Response.ok(users).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@Valid UserRequest request,
                               @PathParam("id") Long id){
        User user = service.updateUser(request, id);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id){
        service.delete(id);
        return Response.noContent().build();
    }
}
