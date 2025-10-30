package com.senac.soundwave.Service;

import com.senac.soundwave.repository.AlbumRepository;
import com.senac.soundwave.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.soundwave.model.Album;
import com.senac.soundwave.model.AlbumDTO;
import com.senac.soundwave.model.Musica;
import com.senac.soundwave.model.MusicaDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository repository;
    Album album = new Album();

    public List<Album> findAll() {
        return repository.findAll();
    }

    public Album upload(AlbumDTO albumDTO) throws IOException {
        String baseDir = "uploads";
        String dirImg = baseDir + "/imagens";
        Files.createDirectories(Paths.get(dirImg));
        String caminhoImagem = null;
        if (albumDTO.getCapa() != null) {
            byte[] dadosImagem = Base64.getDecoder().decode(
                    albumDTO.getCapa().replaceAll("^data:[^,]+,", ""));
            caminhoImagem = dirImg + "/" + albumDTO.getNome() + ".jpg";
            Files.write(Paths.get(caminhoImagem), dadosImagem);
        }
        album.setCapa(caminhoImagem);
        album.setNome(albumDTO.getNome());
        album.setDataLancamento(albumDTO.getDataLancamento());
        return repository.save(album);
    }

    public void deletarAlbum(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Álbum não encontrada com o id: " + id);
        }else{
            repository.deleteById(id);
        }
    }
}
