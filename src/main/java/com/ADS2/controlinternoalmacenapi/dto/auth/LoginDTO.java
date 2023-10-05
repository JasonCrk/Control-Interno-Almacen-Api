package com.ADS2.controlinternoalmacenapi.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    @Email(message = "The email is invalid")
    @NotBlank(message = "The email is required")
    private String email;

    @NotBlank(message = "The password is required")
    private String password;
}
