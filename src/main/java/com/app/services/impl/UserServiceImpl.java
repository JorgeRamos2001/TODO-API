package com.app.services.impl;

import com.app.exceptions.ResourceAlreadyExistException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.exceptions.UpdatePasswordValidationException;
import com.app.mappers.UserMapper;
import com.app.models.dtos.UserDTO;
import com.app.models.entities.User;
import com.app.models.requests.CreateUserRequest;
import com.app.models.requests.UpdatePasswordRequest;
import com.app.models.requests.UpdateUserRequest;
import com.app.repositories.IUserRepository;
import com.app.security.jwt.JwtService;
import com.app.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getMyUser() {
        return userMapper.toUserDto(getAuthenticatedUser());
    }

    @Override
    public UserDTO updateMyUser(UpdateUserRequest user) {

        User userEntity = getAuthenticatedUser();

        if (!user.getEmail().equals(userEntity.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new ResourceAlreadyExistException("User with email: " + user.getEmail() + " already exists.");
            }
        }

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setBirthDate(user.getBirthDate());
        userEntity.setEmail(user.getEmail());

        userEntity.setUpdatedAt(LocalDateTime.now());

        return userMapper.toUserDto(userRepository.save(userEntity));
    }

    @Override
    public UserDTO updateMyPassword(UpdatePasswordRequest request) {

        User user = getAuthenticatedUser();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new UpdatePasswordValidationException("Old password is not correct.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteMyUser() {
        User user = getAuthenticatedUser();
        userRepository.delete(user);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email: " + email + " not found."));
    }
}
