package ru.is_lab1.cache;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cache")
@Produces(MediaType.APPLICATION_JSON)
public class CacheController {
    @Inject
    CacheMetrics stats;

    @Inject
    CacheWrapper wrapper;

    @GET
    @Path("/stats")
    public Response getStat(){
        return Response.ok(stats.getStats()).build();
    }

    @DELETE
    @Path("/clear")
    public Response clear(){
        wrapper.clear();
        return Response.noContent().build();
    }
}
