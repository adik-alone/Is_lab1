package com.example.rest;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

//    @PersistenceContext(unitName = "myPostgresPU")
//    EntityManager em;

    @GET
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id){
        User user = userService.getUserById(id);
        if (user != null){
            return Response.ok(user).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createUser(User user){
        try{
            User created = userService.createUser(user.getUsername(), user.getPassword());
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error creating user: " + e.getMessage())
                    .build();
        }
    }
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User user){
        user.setId(id);
        User update = userService.updateUser(user);
        return Response.ok(update).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id){
        userService.deleteUser(id);
        return Response.status(Response.Status.OK).build();
    }


}
