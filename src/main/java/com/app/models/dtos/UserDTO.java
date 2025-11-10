package com.app.models.dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String role;
}
