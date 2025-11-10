package com.senac.soundwave.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String password;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "artista")
    private Boolean artista = false;
}
