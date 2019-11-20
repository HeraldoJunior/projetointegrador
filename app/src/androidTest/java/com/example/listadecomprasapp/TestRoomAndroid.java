package com.example.listadecomprasapp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.listadecomprasapp.DAO.UsuarioDAO;
import com.example.listadecomprasapp.database.AppDatabase;
import com.example.listadecomprasapp.model.Usuario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4ClassRunner.class)
public class TestRoomAndroid {
    private AppDatabase db;
    private UsuarioDAO usuarioDAO;
    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context,AppDatabase.class).build();
        usuarioDAO = db.usuarioDAO();
    }

    @After
    public void closeDB(){
        db.close();
    }

    @Test
    public void writeUser() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setNome("Valter Branco");
        usuario.setLogin("valter@branco.com");
        usuario.setSenha("teste123");
        long id = usuarioDAO.insert(usuario);
        assertEquals(1,id);
    }
}
