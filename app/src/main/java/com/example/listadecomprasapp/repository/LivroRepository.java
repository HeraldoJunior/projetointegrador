package com.example.listadecomprasapp.repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import com.example.listadecomprasapp.DAO.LivroDAO;
import com.example.listadecomprasapp.database.AppDatabase;
import com.example.listadecomprasapp.model.Livro;

public class LivroRepository {
    private LivroDAO mLivroDAO;
    private List<Livro> mLivros;
    private List<LivroDAO.LivroJoin> mLivrosJoin;

    public LivroRepository(Context context){
        AppDatabase db = AppDatabase.getDatabase(context);
        mLivroDAO = db.livroDAO();
    }

    public List<Livro> getAllLivros(){
        mLivros = mLivroDAO.loadLivros();
        return mLivros;
    }

    public List<LivroDAO.LivroJoin> getAllLivrosJoin(){
        mLivrosJoin = mLivroDAO.loadLivrosJoin();
        return mLivrosJoin;
    }

    public Livro loadLivroByID(long ID) {
        return mLivroDAO.loadLivroByID(ID);
    }

    public void insert(Livro livro){
        new insertAsyncTask(mLivroDAO).execute(livro);
    }
    public void delete(long id){mLivroDAO.delete(id);}
    public void update(Livro livro) {mLivroDAO.update(livro);}

    private static class insertAsyncTask extends AsyncTask<Livro, Void, Void> {

        private LivroDAO mAsyncTaskDAO;

        insertAsyncTask(LivroDAO dao){
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final Livro... params){
            mAsyncTaskDAO.insert(params[0]);
            return null;
        }
    }
}
