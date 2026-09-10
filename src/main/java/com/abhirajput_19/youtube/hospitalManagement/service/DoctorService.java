package com.abhirajput_19.youtube.hospitalManagement.service;

import com.abhirajput_19.youtube.hospitalManagement.dto.DoctorRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Doctor;
import com.abhirajput_19.youtube.hospitalManagement.exception.ResourceNotFoundException;
import com.abhirajput_19.youtube.hospitalManagement.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: " + id
                        ));
    }

    // Create doctor
    public Doctor createDoctor(DoctorRequestDTO doctor) {

        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new IllegalArgumentException(
                    "Doctor already exists with email: "
                            + doctor.getEmail()
            );
        }

        Doctor newDoctor = new Doctor();

        newDoctor.setName(doctor.getName());
        newDoctor.setSpecialization(doctor.getSpecialization());
        newDoctor.setEmail(doctor.getEmail());

        return doctorRepository.save(newDoctor);
    }

    // Update doctor
    public Doctor updateDoctor(
            Long id,
            DoctorRequestDTO doctor) {

        Doctor existingDoctor = getDoctorById(id);

        if (!existingDoctor.getEmail().equals(doctor.getEmail())
                && doctorRepository.existsByEmail(doctor.getEmail())) {

            throw new IllegalArgumentException(
                    "Doctor already exists with email: "
                            + doctor.getEmail()
            );
        }

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setEmail(doctor.getEmail());

        return doctorRepository.save(existingDoctor);
    }

    // Delete doctor
    public void deleteDoctor(Long id) {

        Doctor existingDoctor = getDoctorById(id);

        doctorRepository.delete(existingDoctor);
    }

    public Page<Doctor> searchDoctorsBySpecialization(
            String specialization,
            Pageable pageable) {

        return doctorRepository
                .findBySpecializationContainingIgnoreCase(
                        specialization,
                        pageable
                );
    }
}