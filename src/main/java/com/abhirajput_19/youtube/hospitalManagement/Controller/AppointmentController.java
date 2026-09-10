package com.abhirajput_19.youtube.hospitalManagement.Controller;

import com.abhirajput_19.youtube.hospitalManagement.dto.AppointmentRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Appointment;
import com.abhirajput_19.youtube.hospitalManagement.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }

    // Get all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {

        return ResponseEntity.ok(
                appointmentService.getAllAppointments()
        );
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentById(id)
        );
    }

    // Create appointment
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(
            @Valid @RequestBody AppointmentRequestDTO request) {

        Appointment appointment =
                appointmentService.createAppointment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointment);
    }

    // Update appointment
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequestDTO request) {

        return ResponseEntity.ok(
                appointmentService.updateAppointment(
                        id,
                        request
                )
        );
    }

    // Delete appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable Long id) {

        appointmentService.deleteAppointment(id);

        return ResponseEntity.noContent().build();
    }

    // Get appointments of patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>>
    getAppointmentsByPatient(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                appointmentService
                        .getAppointmentsByPatient(patientId)
        );
    }

    // Get appointments of doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>>
    getAppointmentsByDoctor(
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                appointmentService
                        .getAppointmentsByDoctor(doctorId)
        );
    }
}