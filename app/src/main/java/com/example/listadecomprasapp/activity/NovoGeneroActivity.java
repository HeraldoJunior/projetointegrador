package com.example.listadecomprasapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.listadecomprasapp.model.Genero;
import com.example.listadecomprasapp.repository.Repository;
import com.example.listadecomprasapp.R;

public class NovoGeneroActivity extends Activity {

    private EditText editTitulo;
    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_genero);

        editTitulo = findViewById(R.id.editGenero);
        repository = new Repository(getApplicationContext());
    }

    public void salvarGenero(View view){
        Genero genero = new Genero();
        genero.setNome(editTitulo.getText().toString());

        repository.getGeneroRepository().insert(genero);
        callMainActivity();
    }

    private void callMainActivity() {
        Intent mainActivity = new Intent(NovoGeneroActivity.this,MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
