package com.senac.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.soundwave.model.Musica;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer> { }
