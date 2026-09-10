package com.abhirajput_19.youtube.hospitalManagement.repository;

import com.abhirajput_19.youtube.hospitalManagement.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    boolean existsByEmail(String email);
}
