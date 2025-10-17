package com.senac.soundwave.repository;

import com.senac.soundwave.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.soundwave.model.Musica;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> { }