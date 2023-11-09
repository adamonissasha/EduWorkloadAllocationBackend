package com.example.commonservice.controller;

import com.example.commonservice.dto.request.JwtRequest;
import com.example.commonservice.dto.response.JwtResponse;
import com.example.commonservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return userService.createToken(authRequest);
    }
}
