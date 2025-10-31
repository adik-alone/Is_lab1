package ru.is_lab1.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.dto.request.MovieRequest;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.service.MovieService;

import java.util.List;

@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieController {
    @Inject
    MovieService service;

    @POST
    public Response createMovie(@Valid MovieRequest request){
        Movie createdMovie = service.createMovie(request);
        return Response.status(Response.Status.CREATED).entity(createdMovie).build();
    }

    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") Long id){
        Movie movie = service.getMovieById(id);
        return Response.ok(movie).build();
    }

    @GET
    @Path("/page-{page}-by-{size}")
    public Response getPageMovie(@PathParam("page") int page,
                                 @PathParam("size") int size){
        List<Movie> movies = service.getPageMovie(page, size);
        return Response.ok(movies).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateMovie(@PathParam("id") Long id,
                                MovieRequest request){
        Movie updatedMovie = service.updateMovie(id, request);
        return Response.ok(updatedMovie).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") Long id){
        service.deleteMovie(id);
        return Response.noContent().build();
    }
}
