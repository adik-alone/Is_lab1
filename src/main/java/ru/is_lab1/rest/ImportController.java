package ru.is_lab1.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.FileUploadForm;
import ru.is_lab1.dto.request.ImportRequest;
import ru.is_lab1.dto.request.UserRequest;
import ru.is_lab1.entity.Import;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.service.ImportService;
import ru.is_lab1.service.UserService;

import java.util.Arrays;
import java.util.List;

@Path("/import")
@Produces(MediaType.APPLICATION_JSON)
public class ImportController {

    private final Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Inject
    UserService userService;

    @Inject
    ImportService importService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response importFromFile(@Valid @MultipartForm FileUploadForm form) {
        logger.info("ImportFromFile: start");
        UserRequest userRequest = new UserRequest(form.getLogin(), form.getPassword());
        if (!userService.isUserValid(userRequest)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        logger.info("ImportFromFile: user valid");
        try {
            logger.info("ImportFromFile: start importing");
            logger.info("ImportFromFile: form ==> {}", form);
            List<Movie> uploadedMovies = importService.importMovies(form.getFile());
            importService.createImportResult(new ImportRequest(true, (long) uploadedMovies.size()), userService.getUserByLogin(userRequest.getLogin()).getId());
            return Response.ok(uploadedMovies).build();
        } catch (Exception e) {
            logger.info("ImportFromFile: something fails");
            importService.createImportResult(new ImportRequest(false, 0L), userService.getUserByLogin(userRequest.getLogin()).getId());
//            return Response.status(Response.Status.BAD_REQUEST).entity("Error in import file: " + e.getMessage() + " Error type: " + e.getClass()).build();
            return Response.status(Response.Status.BAD_REQUEST).entity("Error in import file").build();
//            return Response.status(Response.Status.BAD_REQUEST).entity("Error in import file: " + e.getMessage() + " Error type: " + e.getClass() + "\n" + Arrays.toString(e.getStackTrace())).build();
        }
    }

    @POST
    @Path("/get-imports")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getImports(@Valid UserRequest request){
        if (!userService.isUserValid(request)){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Long id = userService.getUserByLogin(request.getLogin()).getId();
        List<Import> imports = importService.getImports(userService.getRole(id), id);
        return Response.ok(imports).build();
    }
}
