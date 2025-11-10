package com.app.services.impl;

import com.app.exceptions.ResourceNotFoundException;
import com.app.mappers.UserMapper;
import com.app.models.dtos.UserDTO;
import com.app.models.entities.User;
import com.app.models.requests.CreateUserRequest;
import com.app.repositories.IUserRepository;
import com.app.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.toUserDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found.")));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @Override
    public UserDTO updateUser(Long id, CreateUserRequest user) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found."));

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setBirthDate(user.getBirthDate());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        userEntity.setUpdatedAt(LocalDateTime.now());

        return userMapper.toUserDto(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found."));
        userRepository.delete(user);
    }
}
