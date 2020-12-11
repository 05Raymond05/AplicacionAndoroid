package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button ingresar;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usern1);
        password = (EditText) findViewById(R.id.pass1);
        ingresar = (Button) findViewById(R.id.ingres1);
        DB = new DBHelper(this);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = username.getText().toString();
                String contraseña = password.getText().toString();

                if(usuario.equals("")||contraseña.equals(""))
                    Toast.makeText(Login.this, "Por favor llene todas las casillas", Toast.LENGTH_SHORT).show();

                else{
                    Boolean checkuserpass = DB.checkusernamepassword(usuario, contraseña);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Ingreso exitoso!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Explicacion.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Credenciales invalidos. Si no ha creado un usuario registrese primero", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}