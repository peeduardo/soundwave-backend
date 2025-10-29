package com.senac.soundwave.controller;

import com.senac.soundwave.Service.MusicaService;
import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.MusicaDTO;
import com.senac.soundwave.repository.MusicaRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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

    @GetMapping("/album/{id}")
    public ResponseEntity<List<Musica>> getMusicasPorAlbum(@PathVariable Integer id) {
        List<Musica> musicas = service.buscarPorAlbum(id);

        if (musicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(musicas);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> adicionar(@RequestParam("nome") String nome,
            @RequestParam("genero") String genero,
            @RequestParam("arquivoMp3") MultipartFile arquivo,
            @RequestParam("idAlbum") Integer idAlbum,
            @RequestParam("dataLancamento") LocalDate dataLancamento,
            @RequestParam("faixa") int faixa,
            @RequestParam("duracaoSegundos") int duracao,
            @RequestParam(value = "imagem") MultipartFile imagem) {
        try {
            MusicaDTO musica = new MusicaDTO();
            musica.setNome(nome);
            musica.setGenero(genero);
            musica.setArquivoMp3(Base64.getEncoder().encodeToString(arquivo.getBytes()));
            musica.setImagem(Base64.getEncoder().encodeToString(imagem.getBytes()));
            musica.setDataLancamento(dataLancamento);
            musica.setDuracaoSegundos(duracao);
            musica.setIdAlbum(idAlbum);
            musica.setFaixa(faixa);
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
