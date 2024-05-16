package com.dam.sstutt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IgnacioProfile extends AppCompatActivity {

    public static String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ignacio_profile);
        usuario = getIntent().getStringExtra("userName");
    }

    public void Ir_Main(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }
}