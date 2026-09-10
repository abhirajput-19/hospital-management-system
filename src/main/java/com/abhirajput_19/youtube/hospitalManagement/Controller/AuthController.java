package com.abhirajput_19.youtube.hospitalManagement.Controller;

import com.abhirajput_19.youtube.hospitalManagement.dto.LoginRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.dto.LoginResponseDTO;
import com.abhirajput_19.youtube.hospitalManagement.dto.RegisterRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.User;
import com.abhirajput_19.youtube.hospitalManagement.service.AuthService;
import com.abhirajput_19.youtube.hospitalManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService,
                          AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        User user = userService.registerUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}