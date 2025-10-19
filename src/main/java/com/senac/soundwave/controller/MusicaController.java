package com.senac.soundwave.controller;

import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.MusicaDTO;
import com.senac.soundwave.model.MusicaService;
import com.senac.soundwave.repository.MusicaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaRepository repository;
    @Autowired
    MusicaService service;

    @GetMapping
    public List<Musica> listar() {
        return service.findAll();
    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> adicionar(@RequestParam("nome") String nome,
                                               @RequestParam("genero") String genero,
                                               @RequestParam("arquivoMp3") MultipartFile arquivo,
                                               @RequestParam(value = "imagem", required = false) MultipartFile imagem) {
        try {
            MusicaDTO musica = new MusicaDTO();
            musica.setNome(nome);
            musica.setGenero(genero);
            musica.setArquivoMp3(Base64.getEncoder().encodeToString(arquivo.getBytes()));
            if (imagem != null) {
                musica.setImagem(Base64.getEncoder().encodeToString(imagem.getBytes()));
            }
            Musica musicaSalva = service.upload(musica);
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Música salva com sucesso!");
            response.put("musica", musicaSalva);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Erro ao salvar a musica");
            response.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
