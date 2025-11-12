package ru.is_lab1.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.dto.request.LocationRequest;
import ru.is_lab1.entity.Location;
import ru.is_lab1.service.LocationService;

import java.util.List;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationController {
    @Inject
    LocationService service;

    @POST
    public Response createLocation(@Valid LocationRequest request){
        Location createdLocation = service.createLocation(request);
        return Response.status(Response.Status.CREATED).entity(createdLocation).build();
    }

    @GET
    @Path("/{id}")
    public Response getLocationById(@PathParam("id") Long id){
        Location location = service.getLocationById(id);
        return Response.ok(location).build();
    }

    @GET
    @Path("/page-{page}-by-{size}")
    public Response getPageLocations(@PathParam("page") int page,
                                     @PathParam("size") int size){
        List<Location> locations = service.getPageLocation(page, size);
        return Response.ok(locations).build();
    }
    @GET
    public Response getAllLocations(){
        List<Location> locations = service.getAllLocation();
        return Response.ok(locations).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateLocation(@PathParam("id") Long id,
                                   @Valid LocationRequest request){
        Location location = service.updateLocation(id, request);
        return Response.ok(location).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLocation(@PathParam("id") Long id){
        service.deleteLocation(id);
        return Response.noContent().build();
    }
}
