package com.app.services;

import com.app.models.dtos.UserDTO;
import com.app.models.requests.CreateUserRequest;

public interface IAuthService {
    public UserDTO registerUser(CreateUserRequest user);
}
