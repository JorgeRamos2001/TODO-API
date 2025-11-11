package com.app.controllers;

import com.app.models.dtos.UserDTO;
import com.app.models.requests.UpdatePasswordRequest;
import com.app.models.requests.UpdateUserRequest;
import com.app.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyUser() {
        return new ResponseEntity<>(userService.getMyUser(), HttpStatus.OK);
    }

    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateMyUser(@Valid @RequestBody UpdateUserRequest request) {
        return new ResponseEntity<>(userService.updateMyUser(request), HttpStatus.OK);
    }

    @PutMapping("/me/password")
    public ResponseEntity<UserDTO> updateMyPassword(@Valid @RequestBody UpdatePasswordRequest request) {
        return new ResponseEntity<>(userService.updateMyPassword(request), HttpStatus.OK);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyUser() {
        userService.deleteMyUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
