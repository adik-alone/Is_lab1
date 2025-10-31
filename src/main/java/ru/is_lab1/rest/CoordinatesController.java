package ru.is_lab1.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.dto.request.CoordinatesRequest;
import ru.is_lab1.entity.Coordinates;
import ru.is_lab1.service.CoordinatesService;

import java.util.List;

@Path("/coordinates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoordinatesController {
//    @Inject
//    CoordinatesService service;
//
//    @POST
//    public Response createCoordinates(@Valid CoordinatesRequest request){
//        Coordinates createdCoordinates = service.createCoordinates(request);
//        return Response.status(Response.Status.CREATED).entity(createdCoordinates).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    public Response getCoordinatesById(@PathParam("id") Long id){
//        Coordinates coordinates = service.getCoordinatesById(id);
//        return Response.ok(coordinates).build();
//    }
//
//    @GET
//    @Path("/page-{page}-by-{size}")
//    public Response getPageCoordinates(@PathParam("page") int page,
//                                       @PathParam("size") int size){
//        List<Coordinates> coordinates = service.getPageCoordinates(page, size);
//        return Response.ok(coordinates).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response updateCoordinates(@PathParam("id") Long id,
//                                      CoordinatesRequest request){
//        Coordinates updatedCoordinates = service.updateCoordinates(id, request);
//        return Response.ok(updatedCoordinates).build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteCoordinates(@PathParam("id") Long id){
//        service.deleteCoordinates(id);
//        return Response.noContent().build();
//    }
}
