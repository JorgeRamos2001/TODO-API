package com.app.mappers;

import com.app.models.dtos.UserDTO;
import com.app.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDto(User user) {
        return UserDTO
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .role(user.getRole().getName())
                .build();
    }
}
