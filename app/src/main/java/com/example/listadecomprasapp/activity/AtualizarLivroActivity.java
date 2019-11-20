package com.example.listadecomprasapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import com.example.listadecomprasapp.adapter.GeneroAdapter;
import com.example.listadecomprasapp.model.Genero;
import com.example.listadecomprasapp.model.Livro;
import com.example.listadecomprasapp.repository.Repository;
import com.example.listadecomprasapp.R;

public class AtualizarLivroActivity extends Activity {
    private EditText editTitulo, editAno;
    private Spinner spinnerGenero;
    private RatingBar ratingLivro;
    private Repository repository;
    private Livro livro;
    private GeneroAdapter generoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_livro);

        long extra_id = getIntent().getLongExtra("ID",0);
        Toast.makeText(this, "ID = " + extra_id, Toast.LENGTH_SHORT).show();

        editTitulo = findViewById(R.id.editTitulo);
        editAno = findViewById(R.id.editAno);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        ratingLivro = findViewById(R.id.ratingNotaLivro);
        repository = new Repository(getApplicationContext());
        loadLivro(extra_id);
        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                livro.setGeneroId(((Genero)adapterView.getItemAtPosition(i)).getID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadLivro(long extra_id) {
        livro = repository.getLivroRepository().loadLivroByID(extra_id);
        generoAdapter = new GeneroAdapter(this,android.R.layout.simple_spinner_item,repository.getGeneroRepository().getAllGeneros());
        spinnerGenero.setAdapter(generoAdapter);
        editAno.setText(String.valueOf(livro.getAno_producao()));
        editTitulo.setText(livro.getTitulo());
        List<Genero> generos = repository.getGeneroRepository().getAllGeneros();
        int counter = 0;
        for(Genero g : generos){
            if(livro.getGeneroId() == g.getID()){
                spinnerGenero.setSelection(counter);
                break;
            }
            counter++;
        }
        ratingLivro.setRating((float)livro.getAvaliacao());
    }

    public void atualizarLivro(View view){
        livro.setTitulo(editTitulo.getText().toString());
        livro.setAno_producao(Integer.parseInt(editAno.getText().toString()));
        livro.setAvaliacao((int)ratingLivro.getRating());
        repository.getLivroRepository().update(livro);
        callMainActivity();
    }

    private void callMainActivity() {
        Intent mainActivity = new Intent(AtualizarLivroActivity.this,MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
