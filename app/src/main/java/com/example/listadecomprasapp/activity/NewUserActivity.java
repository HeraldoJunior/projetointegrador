package com.example.listadecomprasapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadecomprasapp.R;
import com.example.listadecomprasapp.database.AppDatabase;
import com.example.listadecomprasapp.model.Usuario;

public class NewUserActivity extends AppCompatActivity {

    private EditText editNome, editLogin, editSenha1, editSenha2;
    private Button btnCadastrar;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        editNome = findViewById(R.id.editNomeUsuario);
        editLogin = findViewById(R.id.editLoginUsuario);
        editSenha1 = findViewById(R.id.editSenha1);
        editSenha2 = findViewById(R.id.editSenha2);
        btnCadastrar = findViewById(R.id.btnNewUser);

        db = AppDatabase.getDatabase(getApplicationContext());

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }

    private void cadastrar() {
        String nome, login, senha1, senha2;
        nome = editNome.getText().toString().trim();
        login = editLogin.getText().toString().trim();
        senha1 = editSenha1.getText().toString().trim();
        senha2 = editSenha2.getText().toString().trim();

        if(nome.isEmpty()){
            editNome.setError("Campo Obrigatório");
            editNome.requestFocus();
            return;
        }
        if(login.isEmpty()){
            editLogin.setError("Campo Obrigatório");
            editLogin.requestFocus();
            return;
        }
        if(senha1.isEmpty()){
            editSenha1.setError("Campo Obrigatório");
            editSenha1.requestFocus();
            return;
        }
        if(senha2.isEmpty()){
            editSenha2.setError("Campo Obrigatório");
            editSenha2.requestFocus();
            return;
        }
        if(!senha1.equals(senha2)){
            editSenha2.setError("Senhas não conferem");
            editSenha2.requestFocus();
            return;
        }

        Usuario user = new Usuario(nome,login,senha1);

        long ID = db.usuarioDAO().insert(user);

        if(ID > 0){
            Toast.makeText(this, "Usuário Cadastrado!", Toast.LENGTH_SHORT).show();
            loadLogin();
        }
        else{
            Toast.makeText(this, "Erro ao cadastrar o usuário.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadLogin() {
        Intent intent = new Intent(NewUserActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
