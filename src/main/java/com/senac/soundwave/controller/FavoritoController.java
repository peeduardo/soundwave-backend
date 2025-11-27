package com.senac.soundwave.controller;

import com.senac.soundwave.model.Favorito;
import com.senac.soundwave.model.Musica;
import com.senac.soundwave.repository.FavoritoRepository;
import com.senac.soundwave.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<List<Favorito>> listar(@PathVariable Integer idUsuario) {
        List<Favorito> lista = favoritoRepository.findByIdUsuario(idUsuario);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/toggle")
    public ResponseEntity<Map<String, Object>> toggle(@RequestParam Integer idUsuario, @RequestParam Integer idMusica) {
        Optional<Favorito> existente = favoritoRepository.findByIdUsuarioAndMusicaIdMusica(idUsuario, idMusica);
        Map<String, Object> response = new HashMap<>();

        if (existente.isPresent()) {
            favoritoRepository.delete(existente.get());
            response.put("status", "removido");
        } else {
            Musica musica = musicaRepository.findById(idMusica).orElseThrow();
            Favorito novo = new Favorito(idUsuario, musica);
            favoritoRepository.save(novo);
            response.put("status", "adicionado");
        }
        return ResponseEntity.ok(response);
    }
}