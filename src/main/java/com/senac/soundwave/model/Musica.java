package com.senac.soundwave.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMusica;

    @NotNull
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "idAlbum")
    private Integer idAlbum;

    @Column(name = "genero", length = 255)
    private String genero;

    @Lob
    @Column(name = "imagem")
    private byte[] imagem;

    @Lob
    @Column(name = "arquivo_mp3")
    private byte[] arquivoMp3;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "faixa")
    private Integer faixa;

    @Column(name = "duracao_segundos")
    private Integer duracaoSegundos;

    // Getters e Setters
    public Integer getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(Integer idMusica) {
        this.idMusica = idMusica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public byte[] getArquivoMp3() {
        return arquivoMp3;
    }

    public void setArquivoMp3(byte[] arquivoMp3) {
        this.arquivoMp3 = arquivoMp3;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Integer getFaixa() {
        return faixa;
    }

    public void setFaixa(Integer faixa) {
        this.faixa = faixa;
    }

    public Integer getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(Integer duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }
}
