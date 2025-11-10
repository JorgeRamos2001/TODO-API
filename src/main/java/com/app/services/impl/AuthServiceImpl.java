package com.app.services.impl;

import com.app.exceptions.ResourceAlreadyExistException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.mappers.UserMapper;
import com.app.models.dtos.UserDTO;
import com.app.models.entities.Role;
import com.app.models.entities.User;
import com.app.models.requests.CreateUserRequest;
import com.app.repositories.IRoleRepository;
import com.app.repositories.IUserRepository;
import com.app.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDTO registerUser(CreateUserRequest user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistException("User with email: " + user.getEmail() + " already exists.");
        }

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("Role not found with name: ROLE_USER"));

        User userEntity = User
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(role)
                .build();

        return userMapper.toUserDto(userRepository.save(userEntity));
    }
}
