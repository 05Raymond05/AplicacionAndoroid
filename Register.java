package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username, password, repassword;
    Button registrar, ingresar;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.usern);
        password = (EditText) findViewById(R.id.pass);
        repassword = (EditText) findViewById(R.id.repass);
        registrar = (Button) findViewById(R.id.regis);
        ingresar = (Button) findViewById(R.id.ingres);
        DB = new DBHelper(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = username.getText().toString();
                String contraseña = password.getText().toString();
                String recontraseña = repassword.getText().toString();

                //Alguno o todos los espacios vacios a la hora de registrar
                if(usuario.equals("")||contraseña.equals("")||recontraseña.equals(""))
                    Toast.makeText(Register.this, "Porfavor llene todos los espacios", Toast.LENGTH_SHORT).show();

                //Todos los espacios llenos a la hora de registrar
                else{
                    //Ahora revisar si contraseña == recontraseña

                    //Contraseña es igual a recontraseña
                    if(contraseña.equals(recontraseña)){


                        //Ahora revisar si existe o no este usuario
                        Boolean checkuser = DB.checkusername(usuario);

                        //NO existe previamente este usuario que se esta registrando
                        //se ingresan sus datos a la base de datos
                        if(checkuser==false){
                            Boolean insert = DB.insertData(usuario, contraseña);

                            //Si se registran los datos correctamente
                            //Se envia un mensaje y luego se envia al usuario a realizar el login
                            if(insert==true){
                                Toast.makeText(Register.this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);

                             //NO se registran los datos correctamente entonces envia un mensaje al usuario
                            }else{
                                Toast.makeText(Register.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                            }
                        }

                        //Si existe previamente este usuario que se esta registrando
                        else{
                            Toast.makeText(Register.this, "Este usuario ya existe, por favor vaya a la seccion: ya tengo un usuario", Toast.LENGTH_SHORT).show();
                        }

                     //Contraseña NO es igual a recontraseña
                    }else{
                        Toast.makeText(Register.this, "Ingrese la misma contraseña en cada espacio", Toast.LENGTH_SHORT).show();
                    }
                }

            }


        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });



    }
}