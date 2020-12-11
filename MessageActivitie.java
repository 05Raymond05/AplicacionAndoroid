package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivitie extends AppCompatActivity {

    private EditText numero;
    private EditText mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_activitie);

       ActivityCompat.requestPermissions(MessageActivitie.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

        numero = findViewById(R.id.Nume);
        mensaje = findViewById(R.id.Mensa);


    }

    public void EnviarSMS(View view){

        String UnMensaje = mensaje.getText().toString();
        String UnNumero = numero.getText().toString();

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(UnNumero, null, UnMensaje, null,null);

        Toast.makeText(this, "Mensaje enviado correctamente", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MessageActivitie.class);
        startActivity(intent);
        finish();

    }
}