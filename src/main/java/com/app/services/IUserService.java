package com.app.services;

import com.app.models.dtos.UserDTO;
import com.app.models.requests.CreateUserRequest;

import java.util.List;

public interface IUserService {
    public UserDTO getUserById(Long id);
    public List<UserDTO> getAllUsers();
    public UserDTO updateUser(Long id, CreateUserRequest user);
    public void deleteUser(Long id);
}
