package com.abhirajput_19.youtube.hospitalManagement.service;

import com.abhirajput_19.youtube.hospitalManagement.dto.DepartmentRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Department;
import com.abhirajput_19.youtube.hospitalManagement.entity.Doctor;
import com.abhirajput_19.youtube.hospitalManagement.exception.ResourceNotFoundException;
import com.abhirajput_19.youtube.hospitalManagement.repository.DepartmentRepository;
import com.abhirajput_19.youtube.hospitalManagement.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + id
                        ));
    }

    // Create department
    public Department createDepartment(DepartmentRequestDTO request) {

        Department department = new Department();

        department.setName(request.getName());

        return departmentRepository.save(department);
    }

    // Update department
    public Department updateDepartment(Long id,
                                       DepartmentRequestDTO request) {

        Department existingDepartment = getDepartmentById(id);

        existingDepartment.setName(request.getName());

        return departmentRepository.save(existingDepartment);
    }

    // Delete department
    public void deleteDepartment(Long id) {

        Department department = getDepartmentById(id);

        departmentRepository.delete(department);
    }

    // Add doctor to department
    public Department addDoctorToDepartment(Long departmentId,
                                            Long doctorId) {

        Department department = getDepartmentById(departmentId);

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: " + doctorId
                        ));

        department.getDoctors().add(doctor);

        return departmentRepository.save(department);
    }

    // Remove doctor from department
    public Department removeDoctorFromDepartment(Long departmentId,
                                                 Long doctorId) {

        Department department = getDepartmentById(departmentId);

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: " + doctorId
                        ));

        department.getDoctors().remove(doctor);

        return departmentRepository.save(department);
    }

    // Get doctors of a department
    public Set<Doctor> getDoctorsByDepartment(Long departmentId) {

        Department department = getDepartmentById(departmentId);

        return department.getDoctors();
    }
}