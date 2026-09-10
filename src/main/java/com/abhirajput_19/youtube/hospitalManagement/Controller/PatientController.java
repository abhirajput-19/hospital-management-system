package com.abhirajput_19.youtube.hospitalManagement.Controller;

import com.abhirajput_19.youtube.hospitalManagement.dto.PatientRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Insurance;
import com.abhirajput_19.youtube.hospitalManagement.entity.Patient;
import com.abhirajput_19.youtube.hospitalManagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<Page<Patient>> getAllPatients(
            Pageable pageable) {

        return ResponseEntity.ok(
                patientService.getAllPatients(pageable)
        );
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // Create patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(
            @Valid @RequestBody PatientRequestDTO patient) {

        Patient savedPatient = patientService.createPatient(patient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPatient);
    }

    // Update patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequestDTO patient) {

        return ResponseEntity.ok(
                patientService.updatePatient(id, patient)
        );
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }

    // Add insurance to patient
    @PostMapping("/{patientId}/insurance/{insuranceId}")
    public ResponseEntity<Patient> addInsuranceToPatient(
            @PathVariable Long patientId,
            @PathVariable Long insuranceId) {

        return ResponseEntity.ok(
                patientService.addInsuranceToPatient(
                        patientId,
                        insuranceId
                )
        );
    }

    // Get patient's insurance
    @GetMapping("/{patientId}/insurance")
    public ResponseEntity<Insurance> getPatientInsurance(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                patientService.getPatientInsurance(patientId)
        );
    }

    // Remove patient's insurance
    @DeleteMapping("/{patientId}/insurance")
    public ResponseEntity<Void> removePatientInsurance(
            @PathVariable Long patientId) {

        patientService.removePatientInsurance(patientId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Patient>> searchPatients(
            @RequestParam String name,
            Pageable pageable) {

        return ResponseEntity.ok(
                patientService.searchPatientsByName(
                        name,
                        pageable
                )
        );
    }
}