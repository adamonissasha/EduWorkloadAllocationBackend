package com.example.commonservice.controller;

import com.example.commonservice.dto.request.ChangePasswordRequest;
import com.example.commonservice.dto.request.JwtRequest;
import com.example.commonservice.dto.request.NewUserRequest;
import com.example.commonservice.dto.response.JwtResponse;
import com.example.commonservice.dto.response.NewUserResponse;
import com.example.commonservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public NewUserResponse createNewUser(@RequestBody NewUserRequest newUserRequest) {
        return userService.createNewUser(newUserRequest);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return userService.createToken(authRequest);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
    }
}
