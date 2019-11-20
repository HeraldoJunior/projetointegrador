package com.example.listadecomprasapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "livro_table",
        foreignKeys = @ForeignKey(entity = Genero.class,
                parentColumns = "ID",
                childColumns = "genero_id"))
public class Livro {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long id;
    private String titulo;
    private int ano_producao;
    private int avaliacao;

    @ColumnInfo(name = "genero_id")
    private long generoId;

    public Livro() {
    }

    public Livro(long id, String titulo, int ano_producao, int avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.ano_producao = ano_producao;
        this.avaliacao = avaliacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno_producao() {
        return ano_producao;
    }

    public void setAno_producao(int ano_producao) {
        this.ano_producao = ano_producao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(long generoId) {
        this.generoId = generoId;
    }
}
