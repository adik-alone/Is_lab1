package ru.is_lab1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull(message = "login couldn't be null")
    @NotBlank(message = "login couldn't be blank")
    private String login;
    @NotNull(message = "password couldn't be null")
    @NotBlank(message = "password couldn't be blank")
    private String password;
}
