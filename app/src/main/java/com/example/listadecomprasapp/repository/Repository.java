package com.example.listadecomprasapp.repository;

import android.content.Context;

public class Repository {
    private LivroRepository livroRepository;
    private GeneroRepository generoRepository;

    public Repository(Context context){
        livroRepository = new LivroRepository(context);
        generoRepository = new GeneroRepository(context);
    }

    public LivroRepository getLivroRepository() {
        return livroRepository;
    }

    public GeneroRepository getGeneroRepository() {
        return generoRepository;
    }
}
