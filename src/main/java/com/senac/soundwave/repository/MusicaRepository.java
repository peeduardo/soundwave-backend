package com.senac.soundwave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.soundwave.model.Musica;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer> {
     @Query("SELECT m FROM Musica m WHERE m.album.id = :idAlbum")
     List<Musica> findByAlbumId(@Param("idAlbum") Integer idAlbum);
 }
