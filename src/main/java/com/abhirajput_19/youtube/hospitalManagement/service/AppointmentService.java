package com.abhirajput_19.youtube.hospitalManagement.service;

import com.abhirajput_19.youtube.hospitalManagement.dto.AppointmentRequestDTO;
import com.abhirajput_19.youtube.hospitalManagement.entity.Appointment;
import com.abhirajput_19.youtube.hospitalManagement.entity.Doctor;
import com.abhirajput_19.youtube.hospitalManagement.entity.Patient;
import com.abhirajput_19.youtube.hospitalManagement.exception.ResourceNotFoundException;
import com.abhirajput_19.youtube.hospitalManagement.repository.AppointmentRepository;
import com.abhirajput_19.youtube.hospitalManagement.repository.DoctorRepository;
import com.abhirajput_19.youtube.hospitalManagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Appointment getAppointmentById(Long id) {

        return appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Appointment not found with id: " + id
                        ));
    }

    // Create appointment
    public Appointment createAppointment(
            AppointmentRequestDTO request) {

        Patient patient = patientRepository
                .findById(request.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: "
                                        + request.getPatientId()
                        ));

        Doctor doctor = doctorRepository
                .findById(request.getDoctorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: "
                                        + request.getDoctorId()
                        ));

        Appointment appointment = new Appointment();

        appointment.setAppointmentTime(
                request.getAppointmentTime()
        );

        appointment.setReason(request.getReason());

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);
    }

    // Update appointment
    public Appointment updateAppointment(
            Long id,
            AppointmentRequestDTO request) {

        Appointment existingAppointment =
                getAppointmentById(id);

        Patient patient = patientRepository
                .findById(request.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: "
                                        + request.getPatientId()
                        ));

        Doctor doctor = doctorRepository
                .findById(request.getDoctorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: "
                                        + request.getDoctorId()
                        ));

        existingAppointment.setAppointmentTime(
                request.getAppointmentTime()
        );

        existingAppointment.setReason(
                request.getReason()
        );

        existingAppointment.setPatient(patient);
        existingAppointment.setDoctor(doctor);

        return appointmentRepository.save(
                existingAppointment
        );
    }

    // Delete appointment
    public void deleteAppointment(Long id) {

        Appointment appointment =
                getAppointmentById(id);

        appointmentRepository.delete(appointment);
    }

    // Get appointments by patient
    public List<Appointment> getAppointmentsByPatient(
            Long patientId) {

        if (!patientRepository.existsById(patientId)) {
            throw new ResourceNotFoundException(
                    "Patient not found with id: " + patientId
            );
        }

        return appointmentRepository
                .findByPatientId(patientId);
    }

    // Get appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(
            Long doctorId) {

        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException(
                    "Doctor not found with id: " + doctorId
            );
        }

        return appointmentRepository
                .findByDoctorId(doctorId);
    }
}