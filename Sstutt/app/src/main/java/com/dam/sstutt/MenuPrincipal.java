package com.dam.sstutt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }
    public void Ir_Inventario(View view){
        Intent intent = new Intent(this, InventarioActivity.class);
        startActivity(intent);
    }
}