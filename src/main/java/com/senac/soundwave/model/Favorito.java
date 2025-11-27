package com.senac.soundwave.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario")
    private Integer idUsuario; 

    @ManyToOne
    @JoinColumn(name = "id_musica")
    private Musica musica;

    public Favorito() {}

    public Favorito(Integer idUsuario, Musica musica) {
        this.idUsuario = idUsuario;
        this.musica = musica;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    
    public Musica getMusica() { return musica; }
    public void setMusica(Musica musica) { this.musica = musica; }
}