package com.app.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdatePasswordRequest {
    @NotBlank(message = "The old password cannot be empty or null.")
    private String oldPassword;
    @NotBlank(message = "The new password cannot be empty or null.")
    @Size(min = 8, max = 100, message = "New password must be between 8 and 100 characters.")
    private String newPassword;
}
