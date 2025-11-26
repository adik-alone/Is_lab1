package ru.is_lab1.rest;

import jakarta.enterprise.inject.Intercepted;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.is_lab1.dto.request.UserRequest;
import ru.is_lab1.entity.Import;
import ru.is_lab1.service.ImportService;
import ru.is_lab1.service.UserService;

import java.util.List;

@Path("/import-result")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImportResultController {
    @Inject
    private UserService userService;
    @Inject
    private ImportService importService;
    @GET
    @Path("/get-imports")
    public Response getImports(@Valid UserRequest request){
        if (!userService.isUserValid(request)){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Long id = userService.getUserByLogin(request.getLogin()).getId();
        List<Import> imports = importService.getImports(userService.getRole(id), id);
        return Response.ok(imports).build();
    }
}
