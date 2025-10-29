package com.senac.soundwave.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AlbumDTO {
    private Integer idAlbum;
    private String nome;
    private String capa;
    private LocalDate dataLancamento;
}
