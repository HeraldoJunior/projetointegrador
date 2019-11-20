package com.example.listadecomprasapp.repository;

import android.content.Context;

import java.util.List;

import com.example.listadecomprasapp.DAO.GeneroDAO;
import com.example.listadecomprasapp.database.AppDatabase;
import com.example.listadecomprasapp.model.Genero;

public class GeneroRepository {
    private GeneroDAO mGeneroDAO;
    private List<Genero> mGeneros;

    public GeneroRepository(Context context){
        AppDatabase db = AppDatabase.getDatabase(context);
        mGeneroDAO = db.generoDAO();
    }

    public List<Genero> getAllGeneros(){
        mGeneros = mGeneroDAO.loadGeneros();
        return mGeneros;
    }

    /*public Genero loadGeneroByID(long ID) {
        return mGeneroDAO.loadGeneroByID(ID);
    }*/

    public void insert(Genero genero){
        mGeneroDAO.insert(genero);
    }
    public void update(Genero genero) {mGeneroDAO.update(genero);}
}
