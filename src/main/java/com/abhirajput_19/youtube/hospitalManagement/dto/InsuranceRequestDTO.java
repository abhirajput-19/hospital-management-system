package com.abhirajput_19.youtube.hospitalManagement.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InsuranceRequestDTO {

    @NotBlank(message = "Policy number is required")
    private String policyNumber;

    @NotBlank(message = "Provider is required")
    private String provider;

    @NotNull(message = "Valid until date is required")
    @Future(message = "Insurance must be valid in the future")
    private LocalDate validUntil;
}