package com.abhirajput_19.youtube.hospitalManagement.Controller;

import com.abhirajput_19.youtube.hospitalManagement.dto.InsuranceRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Insurance;
import com.abhirajput_19.youtube.hospitalManagement.service.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    // Get all insurances
    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {

        return ResponseEntity.ok(
                insuranceService.getAllInsurances()
        );
    }

    // Get insurance by ID
    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsuranceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                insuranceService.getInsuranceById(id)
        );
    }

    // Create insurance
    @PostMapping
    public ResponseEntity<Insurance> createInsurance(
            @Valid @RequestBody InsuranceRequestDTO request) {

        Insurance insurance =
                insuranceService.createInsurance(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(insurance);
    }

    // Update insurance
    @PutMapping("/{id}")
    public ResponseEntity<Insurance> updateInsurance(
            @PathVariable Long id,
            @Valid @RequestBody InsuranceRequestDTO request) {

        return ResponseEntity.ok(
                insuranceService.updateInsurance(id, request)
        );
    }

    // Delete insurance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(
            @PathVariable Long id) {

        insuranceService.deleteInsurance(id);

        return ResponseEntity.noContent().build();
    }
}