package com.abhirajput_19.youtube.hospitalManagement.service;

import com.abhirajput_19.youtube.hospitalManagement.dto.InsuranceRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Insurance;
import com.abhirajput_19.youtube.hospitalManagement.exception.ResourceNotFoundException;
import com.abhirajput_19.youtube.hospitalManagement.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    // Get all insurances
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    // Get insurance by ID
    public Insurance getInsuranceById(Long id) {

        return insuranceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Insurance not found with id: " + id
                        ));
    }

    // Create insurance
    public Insurance createInsurance(
            InsuranceRequestDTO request) {

        if (insuranceRepository
                .existsByPolicyNumber(request.getPolicyNumber())) {

            throw new IllegalArgumentException(
                    "Insurance already exists with policy number: "
                            + request.getPolicyNumber()
            );
        }

        Insurance insurance = new Insurance();

        insurance.setPolicyNumber(request.getPolicyNumber());
        insurance.setProvider(request.getProvider());
        insurance.setValidUntil(request.getValidUntil());

        return insuranceRepository.save(insurance);
    }

    // Update insurance
    public Insurance updateInsurance(
            Long id,
            InsuranceRequestDTO request) {

        Insurance existingInsurance =
                getInsuranceById(id);

        if (!existingInsurance.getPolicyNumber()
                .equals(request.getPolicyNumber())
                && insuranceRepository
                .existsByPolicyNumber(request.getPolicyNumber())) {

            throw new IllegalArgumentException(
                    "Insurance already exists with policy number: "
                            + request.getPolicyNumber()
            );
        }

        existingInsurance.setPolicyNumber(
                request.getPolicyNumber()
        );

        existingInsurance.setProvider(
                request.getProvider()
        );

        existingInsurance.setValidUntil(
                request.getValidUntil()
        );

        return insuranceRepository.save(
                existingInsurance
        );
    }

    // Delete insurance
    public void deleteInsurance(Long id) {

        Insurance insurance = getInsuranceById(id);

        insuranceRepository.delete(insurance);
    }
}