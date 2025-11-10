package com.app.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "The email cannot be empty o null.")
    @Email(message = "The email is not valid.")
    private String email;

    @NotBlank(message = "The password cannot be empty o null.")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters.")
    private String password;
}
