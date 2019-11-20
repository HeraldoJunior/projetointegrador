package com.example.listadecomprasapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import com.example.listadecomprasapp.DAO.LivroDAO;
import com.example.listadecomprasapp.R;

public class LivroAdapter extends ArrayAdapter<LivroDAO.LivroJoin> {
    private int rId;

    public LivroAdapter(@NonNull Context context, int resource, @NonNull List<LivroDAO.LivroJoin> objects) {
        super(context, resource, objects);
        rId = resource;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(rId,null);
        }

        LivroDAO.LivroJoin livroJoin = getItem(position);

        TextView textTitulo = mView.findViewById(R.id.textNomeLivro);
        TextView textGenero = mView.findViewById(R.id.textGeneroLivro);
        TextView textAno = mView.findViewById(R.id.textAnoLivro);
        RatingBar rating = mView.findViewById(R.id.ratingNotaLivro);

        textTitulo.setText(livroJoin.livro.getTitulo().toUpperCase());
        textGenero.setText(livroJoin.genero.getNome());
        textAno.setText("Ano: " + String.valueOf(livroJoin.livro.getAno_producao()));
        rating.setRating((float)livroJoin.livro.getAvaliacao());

        return mView;
    }
}
