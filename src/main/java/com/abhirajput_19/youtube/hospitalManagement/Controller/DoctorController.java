package com.abhirajput_19.youtube.hospitalManagement.Controller;

import com.abhirajput_19.youtube.hospitalManagement.dto.DoctorRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Doctor;
import com.abhirajput_19.youtube.hospitalManagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Get all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(
                doctorService.getAllDoctors()
        );
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                doctorService.getDoctorById(id)
        );
    }

    // Create doctor
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(
            @Valid @RequestBody DoctorRequestDTO doctor) {

        Doctor savedDoctor = doctorService.createDoctor(doctor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDoctor);
    }

    // Update doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDTO doctor) {

        return ResponseEntity.ok(
                doctorService.updateDoctor(id, doctor)
        );
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Doctor>> searchDoctors(
            @RequestParam String specialization,
            Pageable pageable) {

        return ResponseEntity.ok(
                doctorService.searchDoctorsBySpecialization(
                        specialization,
                        pageable
                )
        );
    }
}