package com.example.commonservice.service;

import com.example.commonservice.dto.request.JwtRequest;
import com.example.commonservice.dto.response.JwtResponse;

public interface UserService {
    JwtResponse createToken(JwtRequest jwtRequest);
}
