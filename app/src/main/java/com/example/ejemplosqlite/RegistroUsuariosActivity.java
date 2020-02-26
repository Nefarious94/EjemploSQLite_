package com.example.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemplosqlite.utilidades.Utilidades;

public class RegistroUsuariosActivity extends AppCompatActivity {

    EditText campoId, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        campoId = (EditText) findViewById(R.id.campoId);
        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoTelefono = (EditText) findViewById(R.id.campoTelefono);
    }

    public void onClick(View view) {
        registrarUsuarios();
        //registrarUsuariosSql();
        campoId.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
    }

    private void registrarUsuariosSql() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values(123,'Jose','1234');

        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO
                + " (" + Utilidades.CAMPO_ID + ","
                + Utilidades.CAMPO_NOMBRE + ","
                + Utilidades.CAMPO_TELEFONO + ")"
                + "VALUES (" + campoId.getText().toString() + ", ''"
                + campoNombre.getText().toString() + " ',' "
                + campoTelefono.getText().toString() + " '')";

        db.execSQL(insert);

        db.close();
    }


    private void registrarUsuarios() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();

    }
    public void registrarUsuariosfab(EditText input, EditText input2, EditText input3) {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(RegistroUsuariosActivity.this, "bd_usuarios", null, 1);

       // SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, input.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, input2.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, input3.getText().toString());

        SQLiteDatabase db = conn.getWritableDatabase();

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
}}
