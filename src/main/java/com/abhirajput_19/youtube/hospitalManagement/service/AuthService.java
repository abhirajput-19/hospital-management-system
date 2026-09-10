package com.abhirajput_19.youtube.hospitalManagement.service;

import com.abhirajput_19.youtube.hospitalManagement.dto.LoginRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.dto.LoginResponseDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.User;
import com.abhirajput_19.youtube.hospitalManagement.repository.UserRepository;
import com.abhirajput_19.youtube.hospitalManagement.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(
                token,
                user.getEmail(),
                user.getRole()
        );
    }
}