package com.abhirajput_19.youtube.hospitalManagement.Controller;

import com.abhirajput_19.youtube.hospitalManagement.dto.DepartmentRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Department;
import com.abhirajput_19.youtube.hospitalManagement.entity.Doctor;
import com.abhirajput_19.youtube.hospitalManagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Get all departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }

    // Get department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    // Create department
    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @Valid @RequestBody DepartmentRequestDTO request) {

        Department department =
                departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(department);
    }

    // Update department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO request) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, request)
        );
    }

    // Delete department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }

    // Add doctor to department
    @PostMapping("/{departmentId}/doctors/{doctorId}")
    public ResponseEntity<Department> addDoctorToDepartment(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                departmentService.addDoctorToDepartment(
                        departmentId,
                        doctorId
                )
        );
    }

    // Remove doctor from department
    @DeleteMapping("/{departmentId}/doctors/{doctorId}")
    public ResponseEntity<Department> removeDoctorFromDepartment(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                departmentService.removeDoctorFromDepartment(
                        departmentId,
                        doctorId
                )
        );
    }

    // Get doctors of department
    @GetMapping("/{departmentId}/doctors")
    public ResponseEntity<Set<Doctor>> getDoctorsByDepartment(
            @PathVariable Long departmentId) {

        return ResponseEntity.ok(
                departmentService.getDoctorsByDepartment(
                        departmentId
                )
        );
    }
}