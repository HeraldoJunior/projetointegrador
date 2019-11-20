package com.example.listadecomprasapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import com.example.listadecomprasapp.DAO.LivroDAO;
import com.example.listadecomprasapp.adapter.LivroAdapter;
import com.example.listadecomprasapp.repository.LivroRepository;
import com.example.listadecomprasapp.R;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView listaLivros;
    private LivroRepository repository;
    ArrayAdapter<LivroDAO.LivroJoin> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLivros = findViewById(R.id.listaLivros);
        repository = new LivroRepository(getApplicationContext());
        atualizarLivros();
        listaLivros.setOnItemClickListener(this);
    }

    public void novoLivro(View view){
        Intent novoLivro = new Intent(MainActivity.this,NovoLivroActivity.class);
        startActivity(novoLivro);
    }

    private void atualizarLivros(){
        List<LivroDAO.LivroJoin> livros = repository.getAllLivrosJoin();
        adapter = new LivroAdapter(getApplicationContext(), R.layout.livro_item, livros);
        listaLivros.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final LivroDAO.LivroJoin livroJoin = (LivroDAO.LivroJoin) adapterView.getItemAtPosition(i);
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("O que fazer com " + livroJoin.livro.getTitulo()).setItems(R.array.opcoes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(which == 0) {
                    repository.delete(livroJoin.livro.getId());
                    atualizarLivros();
                }
                else if(which == 1){
                    callActivity(livroJoin.livro.getId());
                }

            }
        }).create().show();
    }

    private void callActivity(Long id) {
        Intent atualizar = new Intent(MainActivity.this,AtualizarLivroActivity.class);
        atualizar.putExtra("ID",id);
        startActivity(atualizar);
    }

    public void novoGenero(View view) {
        Intent novoGenero = new Intent(MainActivity.this,NovoGeneroActivity.class);
        startActivity(novoGenero);
    }
}
