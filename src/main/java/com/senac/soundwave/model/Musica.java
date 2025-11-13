package com.senac.soundwave.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "musica")
public class Musica {
    @Id
    @Column(name = "idMusica")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMusica;

    @NotNull
    @Column(name = "nome", nullable = false, length = 45)
    @NotNull(message = "Uma musica precisa de um nome")
    private String nome;

    // @Column(name = "idAlbum", nullable = false)
    // @NotNull(message = "Uma musica precisa de um album")
    // private Integer idAlbum;

    @Column(name = "genero", length = 255)
    private String genero;

    @Lob
    @Column(name = "caminho_imagem")
    private String caminho_imagem;

    @Lob
    @Column(name = "caminho_arquivo")
    private String caminho_arquivo;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "faixa")
    private Integer faixa;

    @Column(name = "duracao_segundos")
    private Integer duracaoSegundos;

    @ManyToMany(mappedBy = "musicas")
    @JsonBackReference
    private List<Playlist> playlists = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idAlbum", nullable = false)
    private Album album;

}
