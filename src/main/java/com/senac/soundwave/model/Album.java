package com.senac.soundwave.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "album")
public class Album {
    @Id
    @Column(name = "idAlbum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlbum;

    @NotNull
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Lob
    @Column(name = "capa")
    private String capa;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Musica> musicas;

}
