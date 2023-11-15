package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.ChangePasswordRequest;
import com.example.commonservice.dto.request.JwtRequest;
import com.example.commonservice.dto.request.NewUserRequest;
import com.example.commonservice.dto.response.JwtResponse;
import com.example.commonservice.dto.response.NewUserResponse;
import com.example.commonservice.model.User;
import com.example.commonservice.model.enums.Role;
import com.example.commonservice.repository.UserRepository;
import com.example.commonservice.security.JwtTokenUtil;
import com.example.commonservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtils;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public NewUserResponse createNewUser(NewUserRequest newUserRequest) {
        String username = newUserRequest.getUsername();
        User newUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(newUserRequest.getPassword()))
                .isActive(true)
                .role(Role.ROLE_TEACHER)
                .build();
        newUser = userRepository.save(newUser);
        return NewUserResponse.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .token(generateToken(username))
                .build();
    }

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

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = jwtTokenUtils.getUser();
        checkPassword(user, changePasswordRequest.getPassword());
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        user.setId(user.getId());
        userRepository.save(user);
    }


    private void checkPassword(User user, String password) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), password);
        authenticationManager.authenticate(authToken);
    }

    public String generateToken(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtils.generateAccessToken(userDetails);
    }
}
