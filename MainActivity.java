package com.example.myapplication;
//package in.insideandroid.permission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.telephony.SmsManager;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    Button button, button2, button3, button4, button5, button6, button9;

    String buttonColor, buttonColor2, buttonColor3, buttonColor4;

    ScoreDB DB2;

    Random r;
    int puntaje = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       button = (Button) findViewById(R.id.button);
       button2 = (Button) findViewById(R.id.button2);
       button3 = (Button) findViewById(R.id.button3);
       button4 = (Button) findViewById(R.id.button4);
       button5 = (Button) findViewById(R.id.button5);
       button6 = (Button) findViewById(R.id.button6);
       button9 = (Button) findViewById(R.id.button9);
       //DB2 = new ScoreDB(this);

       //Numero Random para asignarle color inicial a botones
        r = new Random();
        int x,y,z,w;
        x = r.nextInt(3) + 1;
        y = r.nextInt(3) + 1;
        z = r.nextInt(3) + 1;
        w = r.nextInt(3) + 1;

        //Asignar color inicial Boton1
        if(x==1){
            buttonColor = "red";
        } else if (x==2){
            buttonColor = "green";
        } else if (x==3){
            buttonColor = "blue";
        }

        //Asignar color inicial Boton2
        if(y==1){
            buttonColor2 = "red";
        } else if (y==2){
            buttonColor2 = "green";
        } else if (y==3){
            buttonColor2 = "blue";
        }

        //Asignar color inicial Boton3
        if(z==1){
            buttonColor3 = "red";
        } else if (z==2){
            buttonColor3 = "green";
        } else if (z==3){
            buttonColor3 = "blue";
        }

        //Asignar color inicial Boton4
        if(w==1){
            buttonColor4 = "red";
        } else if (w==2){
            buttonColor4 = "green";
        } else if (w==3){
            buttonColor4 = "blue";
        }

        //Aplicar el color asignado a cada boton
        AplicarColor(button, buttonColor);
        AplicarColor(button2, buttonColor2);
        AplicarColor(button3, buttonColor3);
        AplicarColor(button4, buttonColor4);

        //Cambiar color cuando se toca un boton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor=CambiarColor(buttonColor);
                AplicarColor(button, buttonColor);

                buttonColor2=CambiarColor(buttonColor2);
                AplicarColor(button2, buttonColor2);

                RevisarCondicionGadora();


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor=CambiarColor(buttonColor);
                AplicarColor(button, buttonColor);

                buttonColor2=CambiarColor(buttonColor2);
                AplicarColor(button2, buttonColor2);

                buttonColor3=CambiarColor(buttonColor3);
                AplicarColor(button3, buttonColor3);

                RevisarCondicionGadora();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor2=CambiarColor(buttonColor2);
                AplicarColor(button2, buttonColor2);

                buttonColor3=CambiarColor(buttonColor3);
                AplicarColor(button3, buttonColor3);

                buttonColor4=CambiarColor(buttonColor4);
                AplicarColor(button4, buttonColor4);

                RevisarCondicionGadora();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor3=CambiarColor(buttonColor3);
                AplicarColor(button3, buttonColor3);

                buttonColor4=CambiarColor(buttonColor4);
                AplicarColor(button4, buttonColor4);

                RevisarCondicionGadora();

            }
        });

        //Boton para inciar nueva partida.
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Boton para enviar un mensaje por la emocion de haber ganado.
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageActivitie.class);
                startActivity(intent);

            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScoreBoard.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public String CambiarColor(String color){
        if (color.equals("red")){
            color="green";
        }else if(color.equals("green")){
            color = "blue";
        }else if(color.equals("blue")) {
            color="red";
        }
        return color;

    }

    public void AplicarColor(Button button, String color){

        if(color.equals("red")){
            button.setBackgroundColor(Color.RED);
        }else if(color.equals("green")){
            button.setBackgroundColor(Color.GREEN);
        }else if(color.equals("blue")){
            button.setBackgroundColor(Color.BLUE);
        }
    }

    public void RevisarCondicionGadora(){
        puntaje++;
        if (buttonColor.equals("green") &&
                buttonColor2.equals("green") &&
                buttonColor3.equals("green") &&
                buttonColor4.equals("green")){
            Toast.makeText(this, "¡GANASTE en " + puntaje + " movimientos!", Toast.LENGTH_SHORT).show();
           /**
            String puntaje1 = Integer.toString(puntaje);
            String newEntry = puntaje1;
            //String newEntry = puntaje1 + "movimientos";
            AddData(newEntry);
            **/

            //Si hay condicion ganadora se deshabilitan los botones.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button6.setVisibility(View.VISIBLE);

        }
    }

    public void AddData(String newEntry) {
        boolean insertData = DB2.addData(newEntry);

        if (insertData) {
            Toast.makeText(this, "¡GANASTE en " + puntaje + " movimientos!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se guardaron bien tus movimientos", Toast.LENGTH_SHORT).show();
        }
    }
/**
    public void RevisarPermisos(){
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
    } **/

}
