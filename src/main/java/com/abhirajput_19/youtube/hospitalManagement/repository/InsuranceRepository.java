package com.abhirajput_19.youtube.hospitalManagement.repository;

import com.abhirajput_19.youtube.hospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository
        extends JpaRepository<Insurance, Long> {

    boolean existsByPolicyNumber(String policyNumber);
}