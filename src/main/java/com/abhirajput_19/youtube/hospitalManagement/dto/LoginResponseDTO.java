package com.abhirajput_19.youtube.hospitalManagement.dto;

import com.abhirajput_19.youtube.hospitalManagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    private String email;
    private Role role;
}