package com.senac.soundwave.controller;

import com.senac.soundwave.Service.MusicaService;
import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.MusicaDTO;
import com.senac.soundwave.repository.MusicaRepository;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.MusicaDTO;
import com.senac.soundwave.repository.MusicaRepository;

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

    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            // Habilita CORS globalmente para todos os endpoints (/**)
            registry.addMapping("/**")
                    // Permite o acesso do domínio do seu Live Server
                    // Você deve incluir a porta (5500) e o protocolo (http)
                    // Se você usar Live Server, o endereço é geralmente 127.0.0.1 ou localhost
                    .allowedOrigins("http://127.0.0.1:5500")
                    // Permite os métodos que usamos (POST, GET, etc)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    // Permite o envio de cabeçalhos de autenticação, se houver
                    .allowCredentials(true)
                    // Permite todos os cabeçalhos
                    .allowedHeaders("*");
        }
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> adicionar(@RequestParam("nome") String nome,
            @RequestParam("genero") String genero,
            @RequestParam("artista") String artista,
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
            musica.setArtista(artista);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarMusica(@PathVariable Integer id) {
        service.deletarMusica(id);
        return ResponseEntity.noContent().build();
    }
}
