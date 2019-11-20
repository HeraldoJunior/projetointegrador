package com.example.listadecomprasapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.listadecomprasapp.adapter.GeneroAdapter;
import com.example.listadecomprasapp.model.Genero;
import com.example.listadecomprasapp.model.Livro;
import com.example.listadecomprasapp.repository.Repository;
import com.example.listadecomprasapp.R;

public class NovoLivroActivity extends Activity {

    private EditText editTitulo, editAno;
    private Spinner spinnerGenero;
    private RatingBar ratingLivro;
    private Repository repository;
    private Livro livro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_livro);

        editTitulo = findViewById(R.id.editTitulo);
        editAno = findViewById(R.id.editAno);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        ratingLivro = findViewById(R.id.ratingNotaLivro);
        repository = new Repository(getApplicationContext());
        livro = new Livro();
        loadGeneros();
    }

    private void loadGeneros() {
        final GeneroAdapter adapter = new GeneroAdapter(this,android.R.layout.simple_spinner_item,repository.getGeneroRepository().getAllGeneros());
        spinnerGenero.setAdapter(adapter);
        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Genero genero = (Genero) adapterView.getItemAtPosition(i);
                livro.setGeneroId(genero.getID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void salvarLivro(View view){
        //Toast.makeText(this, ""+livro.getGeneroId(), Toast.LENGTH_SHORT).show();
        //Livro livro = new Livro();
        livro.setTitulo(editTitulo.getText().toString());
        livro.setAno_producao(Integer.parseInt(editAno.getText().toString()));
        livro.setAvaliacao((int)ratingLivro.getRating());
        repository.getLivroRepository().insert(livro);
        callMainActivity();
    }

    private void callMainActivity() {
        Intent mainActivity = new Intent(NovoLivroActivity.this,MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
