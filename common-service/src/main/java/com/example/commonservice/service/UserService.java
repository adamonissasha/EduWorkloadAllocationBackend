package com.example.commonservice.service;

import com.example.commonservice.dto.request.ChangePasswordRequest;
import com.example.commonservice.dto.request.JwtRequest;
import com.example.commonservice.dto.request.NewUserRequest;
import com.example.commonservice.dto.response.JwtResponse;
import com.example.commonservice.dto.response.NewUserResponse;

public interface UserService {
    NewUserResponse createNewUser(NewUserRequest newUserRequest);

    JwtResponse createToken(JwtRequest jwtRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);
}
