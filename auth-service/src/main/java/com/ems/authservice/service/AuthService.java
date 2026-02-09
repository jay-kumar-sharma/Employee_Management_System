package com.ems.authservice.service;

import com.ems.authservice.dto.*;
import com.ems.authservice.entity.User;
import com.ems.authservice.repository.UserRepository;
import com.ems.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String register(RegisterRequest req) {

        if (repo.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        User user = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .build();

        repo.save(user);
        return "User Registered Successfully!";
    }

    public AuthResponse login(LoginRequest req) {

        User user = repo.findByUsername(req.getUsername());

        if (user == null || !encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials!");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return new AuthResponse(token, user.getUsername(), user.getRole());
    }
}
