package com.senac.soundwave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.soundwave.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
