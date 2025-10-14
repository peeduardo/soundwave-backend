package com.senac.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.soundwave.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long>{
    
}
