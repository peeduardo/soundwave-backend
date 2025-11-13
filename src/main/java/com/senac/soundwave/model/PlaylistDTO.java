package com.senac.soundwave.model;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistDTO {
    private String nome;
    private List<Integer> idMusicas;
    private Integer idUsuario;
}
