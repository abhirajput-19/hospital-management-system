package com.abhirajput_19.youtube.hospitalManagement.service;

import com.abhirajput_19.youtube.hospitalManagement.dto.PatientRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Patient;
import com.abhirajput_19.youtube.hospitalManagement.repository.PatientRepository;
import com.abhirajput_19.youtube.hospitalManagement.entity.Insurance;
import com.abhirajput_19.youtube.hospitalManagement.exception.ResourceNotFoundException;
import com.abhirajput_19.youtube.hospitalManagement.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    // Get patients with pagination and sorting
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    // Get patient by ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with id: " + id));
    }

    // Create patient
    public Patient createPatient(PatientRequestDTO patient) {

        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new IllegalArgumentException(
                    "Patient already exists with email: "
                            + patient.getEmail()
            );
        }

        Patient newPatient = new Patient();

        newPatient.setName(patient.getName());
        newPatient.setBirthDate(patient.getBirthDate());
        newPatient.setEmail(patient.getEmail());
        newPatient.setGender(patient.getGender());
        newPatient.setBloodGroup(patient.getBloodGroup());

        return patientRepository.save(newPatient);
    }

    // Update patient
    public Patient updatePatient(
            Long id,
            PatientRequestDTO patient) {

        Patient existingPatient = getPatientById(id);

        if (!existingPatient.getEmail().equals(patient.getEmail())
                && patientRepository.existsByEmail(patient.getEmail())) {

            throw new IllegalArgumentException(
                    "Patient already exists with email: "
                            + patient.getEmail()
            );
        }

        existingPatient.setName(patient.getName());
        existingPatient.setBirthDate(patient.getBirthDate());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setGender(patient.getGender());
        existingPatient.setBloodGroup(patient.getBloodGroup());

        return patientRepository.save(existingPatient);
    }

    // Delete patient
    public void deletePatient(Long id) {

        Patient existingPatient = getPatientById(id);

        patientRepository.delete(existingPatient);
    }

    public Patient addInsuranceToPatient(
            Long patientId,
            Long insuranceId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: " + patientId));

        Insurance insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Insurance not found with id: " + insuranceId));

        patient.setInsurance(insurance);

        return patientRepository.save(patient);
    }

    public Insurance getPatientInsurance(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: " + patientId));

        if (patient.getInsurance() == null) {
            throw new ResourceNotFoundException(
                    "Insurance not found for patient id: " + patientId);
        }

        return patient.getInsurance();
    }

    public void removePatientInsurance(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: " + patientId));

        patient.setInsurance(null);

        patientRepository.save(patient);
    }

    public Page<Patient> searchPatientsByName(
            String name,
            Pageable pageable) {

        return patientRepository
                .findByNameContainingIgnoreCase(name, pageable);
    }
}