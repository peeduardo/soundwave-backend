package com.senac.soundwave.repository;

import com.senac.soundwave.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    
    List<Favorito> findByIdUsuario(Integer idUsuario);
    
    Optional<Favorito> findByIdUsuarioAndMusicaIdMusica(Integer idUsuario, Integer idMusica);
}