package com.dam.sstutt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainRootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_root);
    }

    public void Ir_Pruebas(View view){
        Intent intent = new Intent(this, PruebasActivity.class);
        startActivity(intent);
    }
    public void Ir_Login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}