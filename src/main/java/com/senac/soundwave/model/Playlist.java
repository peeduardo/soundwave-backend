package com.senac.soundwave.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlaylist")
    private Integer idPlaylist;

    @NotNull
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;


    @Column(name = "Usuario_idUsuario")
    private Integer idUsuario;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "playlist_musica",
            joinColumns = @JoinColumn(name = "Playlist_idPlaylist"),
            inverseJoinColumns = @JoinColumn(name = "Musica_idMusica")
    )
    @JsonManagedReference
    private List<Musica> musicas = new ArrayList<>();
}
