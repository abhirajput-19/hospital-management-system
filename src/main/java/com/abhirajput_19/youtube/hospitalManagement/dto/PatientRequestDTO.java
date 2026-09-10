package com.abhirajput_19.youtube.hospitalManagement.dto;

import com.abhirajput_19.youtube.hospitalManagement.entity.type.BloodGroupType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Blood group is required")
    private BloodGroupType bloodGroup;
}