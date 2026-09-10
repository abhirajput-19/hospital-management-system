package com.abhirajput_19.youtube.hospitalManagement.repository;

import com.abhirajput_19.youtube.hospitalManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}