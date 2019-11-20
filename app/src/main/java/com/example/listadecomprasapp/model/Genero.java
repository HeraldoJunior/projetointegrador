package com.example.listadecomprasapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "genero_table")
public class Genero {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long ID;
    private String nome;

    public Genero(){}

    public Genero(long ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
