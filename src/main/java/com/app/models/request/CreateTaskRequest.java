package com.app.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateTaskRequest {

    @NotBlank(message = "Task title cannot be empty or null.")
    @Size(max = 100, message = "Task title must be less than 100 characters.")
    private String title;

    @NotBlank(message = "Task description cannot be empty or null.")
    @Size(max = 350, message = "Task description must be less than 350 characters.")
    private String description;
}
