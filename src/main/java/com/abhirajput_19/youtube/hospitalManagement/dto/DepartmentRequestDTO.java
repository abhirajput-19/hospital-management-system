package com.abhirajput_19.youtube.hospitalManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is required")
    private String name;
}