package com.abhirajput_19.youtube.hospitalManagement.repository;

import com.abhirajput_19.youtube.hospitalManagement.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findBySpecializationContainingIgnoreCase(
            String specialization,
            Pageable pageable
    );

    boolean existsByEmail(String email);
}