package com.example.listadecomprasapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.listadecomprasapp.DAO.UsuarioDAO;
import com.example.listadecomprasapp.model.Genero;
import com.example.listadecomprasapp.model.Usuario;
import com.example.listadecomprasapp.util.Converters;
import com.example.listadecomprasapp.DAO.GeneroDAO;
import com.example.listadecomprasapp.DAO.LivroDAO;

import com.example.listadecomprasapp.model.Livro;


@Database(entities = {Usuario.class,Livro.class, Genero.class},
            version = 1)
@TypeConverters(value = Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    public abstract UsuarioDAO usuarioDAO();
    public abstract LivroDAO livroDAO();
    public abstract GeneroDAO generoDAO();

    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"livro_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
