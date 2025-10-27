package com.senac.soundwave.model;

import com.senac.soundwave.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senac.soundwave.model.Musica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class MusicaService {
    @Autowired
    private MusicaRepository repository;
    Musica musica = new Musica();

    public List<Musica> findAll(){
       return repository.findAll();
    }

    public Musica upload(MusicaDTO musicaDTO) throws IOException {
        String baseDir = "uploads";
        String dirMp3 = baseDir + "/mp3";
        String dirImg = baseDir + "/imagens";
        Files.createDirectories(Paths.get(dirMp3));
        Files.createDirectories(Paths.get(dirImg));
        String caminhoMp3 = null;
        if (musicaDTO.getArquivoMp3() != null) {
            byte[] dadosMp3 = Base64.getDecoder().decode(
                    musicaDTO.getArquivoMp3().replaceAll("^data:[^,]+,", ""));
            caminhoMp3 = dirMp3 + "/" + musicaDTO.getNome() + ".mp3";
            Files.write(Paths.get(caminhoMp3), dadosMp3);
        }

        String caminhoImagem = null;
        if (musicaDTO.getImagem() != null) {
            byte[] dadosImagem =  Base64.getDecoder().decode(
                    musicaDTO.getImagem().replaceAll("^data:[^,]+,", ""));
            caminhoImagem = dirImg + "/" + musicaDTO.getNome() + ".jpg";
            Files.write(Paths.get(caminhoImagem), dadosImagem);
        }


        musica.setNome(musicaDTO.getNome());
        musica.setGenero(musicaDTO.getGenero());
        musica.setIdAlbum(musicaDTO.getIdAlbum());
        musica.setDataLancamento(musicaDTO.getDataLancamento());
        musica.setFaixa(musicaDTO.getFaixa());
        musica.setDuracaoSegundos(musicaDTO.getDuracaoSegundos());
        musica.setCaminho_arquivo(caminhoMp3);
        musica.setCaminho_imagem(caminhoImagem);

        return repository.save(musica);
    }
}
