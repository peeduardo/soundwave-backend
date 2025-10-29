package com.senac.soundwave.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.senac.soundwave.Service.AlbumService;
import com.senac.soundwave.Service.MusicaService;
import com.senac.soundwave.model.Album;
import com.senac.soundwave.model.AlbumDTO;
import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.MusicaDTO;
import com.senac.soundwave.model.Playlist;
import com.senac.soundwave.repository.AlbumRepository;
import com.senac.soundwave.repository.MusicaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/albuns")
public class AlbumController {
     @Autowired
    private AlbumRepository repository;
    @Autowired
    AlbumService service;

    @GetMapping
    public List<Album> listar() {
        return service.findAll();
    }

    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> adicionar(@RequestParam("nome") String nome,
            @RequestParam("dataLancamento") LocalDate dataLancamento,
            @RequestParam(value = "capa") MultipartFile imagem) {
        try {
            AlbumDTO album = new AlbumDTO();
            album.setNome(nome);
            album.setCapa(Base64.getEncoder().encodeToString(imagem.getBytes()));
            album.setDataLancamento(dataLancamento);
            Album albumSalvo = service.upload(album);
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Album salvo com sucesso!");
            response.put("musica", albumSalvo);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Erro ao salvar a album");
            response.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
