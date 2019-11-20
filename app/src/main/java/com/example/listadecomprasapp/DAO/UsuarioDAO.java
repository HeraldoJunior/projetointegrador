package com.example.listadecomprasapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listadecomprasapp.model.Usuario;

@Dao
public interface UsuarioDAO {
    @Insert
    long insert(Usuario usuario);

    @Update
    int update(Usuario usuario);

    @Query("SELECT * from tbl_usuario where usuario_login = :login and usuario_senha = :senha")
    Usuario getUser(String login,String senha);

    @Query("SELECT * from tbl_usuario where usuario_id = :id")
    Usuario getUserById(long id);

}
