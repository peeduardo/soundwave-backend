package com.senac.soundwave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.soundwave.user.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
