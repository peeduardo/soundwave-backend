package com.senac.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.soundwave.model.Album;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> { }
