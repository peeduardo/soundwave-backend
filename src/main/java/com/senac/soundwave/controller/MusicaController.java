package com.senac.soundwave.controller;

import com.senac.soundwave.model.Musica;
import com.senac.soundwave.repository.MusicaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaRepository repository;

    @GetMapping
    public List<Musica> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Musica adicionar(@RequestBody Musica musica) {
        return repository.save(musica);
    }
}
