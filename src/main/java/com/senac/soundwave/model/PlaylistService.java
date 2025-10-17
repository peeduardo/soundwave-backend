package com.senac.soundwave.model;

import com.senac.soundwave.repository.MusicaRepository;
import com.senac.soundwave.repository.PlaylistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository repository;
    @Autowired
    private MusicaRepository musica;

    public Playlist buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));
    }

    @Transactional
    public Playlist criarPlaylist(PlaylistDTO dto) {
        Playlist playlist = new Playlist();
        playlist.setNome(dto.getNome());
        if (dto.getIdMusicas() != null && !dto.getIdMusicas().isEmpty()) {
            List<Musica> musicas = musica.findAllById(dto.getIdMusicas());
            playlist.setMusicas(musicas);
        }
        return repository.save(playlist);
    }

    public Playlist adicionarMusica(List<Integer> idMusicas,Integer id){
        Playlist playlist =  repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));
        List<Musica> musicas = musica.findAllById(idMusicas);

        for (Musica musica : musicas) {
            if (!playlist.getMusicas().contains(musica)) {
                playlist.getMusicas().add(musica);
            }
        }
        return repository.save(playlist);
    }
}