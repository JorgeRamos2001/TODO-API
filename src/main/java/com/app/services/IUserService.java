package com.app.services;

import com.app.models.dtos.UserDTO;
import com.app.models.requests.CreateUserRequest;
import com.app.models.requests.UpdatePasswordRequest;
import com.app.models.requests.UpdateUserRequest;

import java.util.List;

public interface IUserService {
    public UserDTO getMyUser();
    public UserDTO updateMyUser(UpdateUserRequest user);
    public UserDTO updateMyPassword(UpdatePasswordRequest request);
    public void deleteMyUser();
}
