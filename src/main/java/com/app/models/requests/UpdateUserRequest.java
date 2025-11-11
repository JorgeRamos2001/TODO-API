package com.app.models.requests;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserRequest {

    @NotBlank(message = "First name cannot be empty or null.")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters.")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty or null.")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters.")
    private String lastName;

    @NotNull(message = "Birth date cannot be null.")
    @Past(message = "Birth date must be a past date.")
    private LocalDate birthDate;

    @NotBlank(message = "Email cannot be empty or null.")
    @Email(message = "Email is not valid.")
    private String email;
}
