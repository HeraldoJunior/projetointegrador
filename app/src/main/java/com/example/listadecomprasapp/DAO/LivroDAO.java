package com.example.listadecomprasapp.DAO;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.listadecomprasapp.model.Genero;
import com.example.listadecomprasapp.model.Livro;


@Dao
public interface LivroDAO {

    @Insert
    void insert(Livro livro);

    @Update
    void update(Livro livro);

    @Query("SELECT * FROM livro_table WHERE livro_table.ID == :id")
    Livro loadLivroByID(Long id);

    @Query("DELETE FROM livro_table where livro_table.ID == :id")
    void delete(long id);

    @Query("SELECT * from livro_table ORDER BY avaliacao DESC")
    List<Livro> loadLivros();

    @Query("SELECT livro_table.ID,livro_table.titulo,livro_table.ano_producao,livro_table.avaliacao, genero_table.ID as genero_ID ,genero_table.nome as genero_nome from livro_table INNER JOIN genero_table ON livro_table.genero_id = genero_table.ID ORDER BY avaliacao DESC")
    List<LivroJoin> loadLivrosJoin();

    @Query("SELECT titulo from livro_table")
    List<String> loadLivrosNames();

    static class LivroJoin{
        @Embedded
        public Livro livro;
        @Embedded(prefix = "genero_")
        public Genero genero;
    }
}
