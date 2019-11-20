package com.example.listadecomprasapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.listadecomprasapp.model.Genero;

@Dao
public interface GeneroDAO {
    @Insert
    void insert(Genero genero);

    @Update
    void update(Genero genero);

    @Query("SELECT * from genero_table ORDER BY nome DESC")
    List<Genero> loadGeneros();
}
