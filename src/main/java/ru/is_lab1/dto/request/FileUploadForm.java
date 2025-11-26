package ru.is_lab1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import lombok.Data;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

@Data
public class FileUploadForm {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream file;
    @FormParam("login")
    @NotNull
    @NotBlank
    private String login;
    @FormParam("password")
    @NotNull
    @NotEmpty
    private String password;
}
