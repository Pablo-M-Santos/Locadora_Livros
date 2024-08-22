package com.locadora.locadoraLivro.Users.DTOs;

import com.locadora.locadoraLivro.Users.models.UserRoleEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

public record CreateUserRequestDTO(
        @NotEmpty(message = "Name cannot be empty") String name,
        @Email(message = "Email should be valid") @NotEmpty(message = "Email cannot be empty") String email,
        @NotEmpty(message = "Password cannot be empty") String password,
        @NotNull(message = "Role cannot be null") UserRoleEnum role
) {}
