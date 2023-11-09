package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.JwtRequest;
import com.example.commonservice.dto.response.JwtResponse;
import com.example.commonservice.service.UserService;
import com.example.commonservice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtils;
    private final UserDetailsService userDetailsService;

    @Override
    public JwtResponse createToken(JwtRequest jwtRequest) throws BadCredentialsException {
        String username = jwtRequest.getUsername();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, jwtRequest.getPassword()));
        String token = generateToken(username);
        return JwtResponse.builder()
                .token(token)
                .build();
    }

    public String generateToken(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtils.generateAccessToken(userDetails);
    }
}
