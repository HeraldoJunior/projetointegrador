package com.example.listadecomprasapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.listadecomprasapp.R;
import com.example.listadecomprasapp.database.AppDatabase;
import com.example.listadecomprasapp.util.Util;

public class DashActivity extends AppCompatActivity {

    private TextView textWelcome;
    private Button btnLogout;
    private AppDatabase db;
    private SharedPreferences preferences;
    private Button btnIrListaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        btnLogout = findViewById(R.id.btnLogout);
        textWelcome = findViewById(R.id.textWelcome);
        btnIrListaLivros = findViewById(R.id.btnIrListaLivros);

        db = AppDatabase.getDatabase(getApplicationContext());

        preferences = getSharedPreferences(Util.pref_name, Context.MODE_PRIVATE);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        btnIrListaLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadListaLivros();
            }
        });

        loadWelcomeText();
    }

    private void loadWelcomeText() {
        long user_id = preferences.getLong("id",0);
        String nome = db.usuarioDAO().getUserById(user_id).getNome();
        textWelcome.setText("Bem-vindo! "+nome);
    }

    private void logout() {
        Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        loadLogin();
    }

    private void loadLogin() {
        Intent intent = new Intent(DashActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private void loadListaLivros() {
        Intent intent2 = new Intent(DashActivity.this,MainActivity.class);
        startActivity(intent2);
    }
}