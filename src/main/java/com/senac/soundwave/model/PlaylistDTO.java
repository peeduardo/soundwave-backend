package com.senac.soundwave.model;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistDTO {
    private String nome;
    private List<Integer> idMusicas;

    //depois adicionar os outros stributos IdUsuario e etc
}
