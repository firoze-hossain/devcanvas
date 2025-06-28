package com.roze.devcanvas.dto;

import com.roze.devcanvas.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Username can not be empty")
    @NotNull(message = "Username can not be null")
    private String username;
    @NotBlank(message = "Email can not be empty")
    @NotNull(message = "Email can not be null")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password can not be empty")
    @NotNull(message = "Password can not be null")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
