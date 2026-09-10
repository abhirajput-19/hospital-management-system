package com.abhirajput_19.youtube.hospitalManagement.service;

import com.abhirajput_19.youtube.hospitalManagement.dto.RegisterRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.User;
import com.abhirajput_19.youtube.hospitalManagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "User already exists with email: "
                            + request.getEmail()
            );
        }

        User user = new User();

        user.setEmail(request.getEmail());

        // Never store plain password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        return userRepository.save(user);
    }
}