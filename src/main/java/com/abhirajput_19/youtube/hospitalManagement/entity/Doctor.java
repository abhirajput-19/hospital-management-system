package com.abhirajput_19.youtube.hospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @ManyToMany(mappedBy = "doctors")
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();
}