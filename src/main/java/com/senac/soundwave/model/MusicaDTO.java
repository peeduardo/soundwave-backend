package com.senac.soundwave.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class MusicaDTO {
    private Integer idMusica;
    private String nome;
    private Integer idAlbum;
    private String genero;
    private String imagem;
    private String arquivoMp3;
    private LocalDate dataLancamento;
    private Integer faixa;
    private Integer duracaoSegundos;
    private List<Integer> playlistsIds;
}
