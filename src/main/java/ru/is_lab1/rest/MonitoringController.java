package ru.is_lab1.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.service.DruidMonitoringService;

import java.sql.SQLException;

@Path("/monitoring")
@Produces(MediaType.APPLICATION_JSON)
public class MonitoringController {

    @Inject
    private DruidMonitoringService service;

    @GET
    @Path("/druid-stats")
    public Response getDruidStats(){
        try{
            String stats = service.getDruidStats();
            return Response.ok(stats).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error getting Druid stats: " + e.getMessage())
                    .build();
        }
    }
}
