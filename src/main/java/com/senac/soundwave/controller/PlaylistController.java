package com.senac.soundwave.controller;

import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.Playlist;
import com.senac.soundwave.model.PlaylistDTO;
import com.senac.soundwave.model.PlaylistService;
import com.senac.soundwave.repository.MusicaRepository;
import com.senac.soundwave.repository.PlaylistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Playlist> criarPlaylist(@RequestBody @Valid PlaylistDTO dto) {
        Playlist playlistCreated = service.criarPlaylist(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistCreated);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Playlist> atualizarPlaylist(@PathVariable Integer id,@RequestBody @Valid List<Integer> musicas ) {
        Playlist playlistCreated = service.adicionarMusica(musicas, id);
        return ResponseEntity.ok(playlistCreated);
    }
}
