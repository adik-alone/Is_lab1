package ru.is_lab1.rest;

import jakarta.faces.annotation.RequestParameterMap;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.entity.enums.MovieGenre;
import ru.is_lab1.service.SpecialApplicationService;

import java.util.List;

@Path("/special")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialApplicationController {
    @Inject
    SpecialApplicationService service;

    @DELETE
    @Path("/delete-one-movie-by-genre")
    public Response deleteOneMovieByGenre(@QueryParam("genre") MovieGenre genre){
        service.deleteOneMovieGenre(genre);
        return Response.noContent().build();
    }

    @GET
    @Path("/group-movie-by-box-office")
    public Response groupMovieByTotalBoxOffice(){
        List<Long> groupList = service.groupMovieByTotalBoxOffice();
        return Response.ok(groupList).build();
    }

    @GET
    @Path("/movie-with-golden-palm-count-equals")
    public Response movieWithGoldenPalmCountEqualsValue(@QueryParam("value") int value){
        Long count = service.movieWithGoldenPalmEqualsValue(value);
        return Response.ok(count).build();
    }
}
