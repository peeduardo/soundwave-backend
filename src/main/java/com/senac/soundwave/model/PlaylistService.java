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

    public List<Playlist> findAll(){
        return repository.findAll();
    }

    public Playlist buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));
    }

    // ==========================================================
    // --- CORREÇÃO DE SINTAXE (voltando para .get...() ) ---
    // ==========================================================
    @Transactional
    public Playlist criarPlaylist(PlaylistDTO dto) {
        Playlist playlist = new Playlist();

        // CORRIGIDO: de dto.nome() para dto.getNome()
        playlist.setNome(dto.getNome());

        // CORRIGIDO: de dto.idUsuario() para dto.getIdUsuario()
        playlist.setIdUsuario(dto.getIdUsuario());

        // CORRIGIDO: de dto.idMusicas() para dto.getIdMusicas()
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

        // Agora que o Lombok está funcionando, .getMusicas() existe!
        for (Musica musica : musicas) {
            if (!playlist.getMusicas().contains(musica)) {
                playlist.getMusicas().add(musica);
            }
        }
        return repository.save(playlist);
    }

    // ==========================================================
    // --- FUNÇÕES DE EDITAR/EXCLUIR ---
    // ==========================================================
    @Transactional
    public Playlist editarNome(Integer id, String novoNome) {
        Playlist playlist = buscarPorId(id);
        playlist.setNome(novoNome); // Agora .setNome() existe!
        return repository.save(playlist);
    }

    public void excluirPlaylist(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Playlist não encontrada para exclusão");
        }
        repository.deleteById(id);
    }
}