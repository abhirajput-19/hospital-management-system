package com.abhirajput_19.youtube.hospitalManagement.repository;

import com.abhirajput_19.youtube.hospitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}