package com.senac.soundwave.controller;

import com.senac.soundwave.Service.PlaylistService;
import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.Playlist;
import com.senac.soundwave.model.PlaylistDTO;
import com.senac.soundwave.repository.MusicaRepository;
import com.senac.soundwave.repository.PlaylistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

// --- IMPORTS NOVOS ADICIONADOS ---
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Map;


@RestController
@RequestMapping("/playlists")


public class PlaylistController {
    @Autowired
    private PlaylistRepository repository;
    @Autowired
    private PlaylistService service;

    @GetMapping
    public List<Playlist> listar() {
        return service.findAll();
    }

    // ==========================================================
    // --- NOVA FUNÇÃO ADICIONADA ---
    /**
     * Busca uma playlist específica pelo seu ID.
     * @param id O ID da playlist (vem da URL, ex: /playlists/5)
     * @return A playlist encontrada ou um erro 404 (via service)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> buscarPlaylistPorId(@PathVariable Integer id) {
        Playlist playlist = service.buscarPorId(id);
        return ResponseEntity.ok(playlist);
    }

    @PostMapping
    public ResponseEntity<Playlist> criarPlaylist(@RequestBody @Valid PlaylistDTO dto) {
        Playlist playlistCreated = service.criarPlaylist(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistCreated);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Playlist> atualizarPlaylist(@PathVariable Integer id,
            @RequestBody @Valid List<Integer> musicas) {
        Playlist playlistCreated = service.adicionarMusica(musicas, id);
        return ResponseEntity.ok(playlistCreated);
    }

    // ==========================================================
    // --- FUNÇÃO DE EDITAR NOME (ADICIONADA) ---
    // ==========================================================
    @PutMapping("/{id}/editarNome")
    public ResponseEntity<Playlist> editarNomePlaylist(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {

        String novoNome = body.get("nome");
        Playlist playlistAtualizada = service.editarNome(id, novoNome);
        return ResponseEntity.ok(playlistAtualizada);
    }

    // ==========================================================
    // --- FUNÇÃO DE EXCLUIR PLAYLIST (ADICIONADA) ---
    // ==========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPlaylist(@PathVariable Integer id) {
        service.excluirPlaylist(id);
        return ResponseEntity.noContent().build(); // Retorna 204 (Sucesso, sem conteúdo)
    }
}