package com.example.commonservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.commonservice.model.User;
import com.example.commonservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    private final UserRepository userRepository;

    private static final String JWT_CLAIM_USERNAME = "username";
    private static final String JWT_CLAIM_ROLE = "role";
    private static final String USERNAME_NOT_FOUND = "Нет пользователя с таким именем!";

    public String generateAccessToken(UserDetails user) {
        return JWT.create()
                .withClaim(JWT_CLAIM_USERNAME, user.getUsername())
                .withClaim(JWT_CLAIM_ROLE, user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + lifetime.toMillis()))
                .sign(Algorithm.HMAC256(secret));
    }

    public void validateToken(String token) throws TokenExpiredException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .build();
        verifier.verify(token);
    }

    public String getUsername(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(JWT_CLAIM_USERNAME).asString();
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND));
    }
}
