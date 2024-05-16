package com.dam.sstutt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String usuario;
    private Button botonPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = getIntent().getStringExtra("userName");

        botonPerfil = (Button) findViewById(R.id.btPerfil);

        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ir_Perfil(view);
            }
        });

    }


    private void lanzarActividadSolicitarPrenda() {
        Intent intent = new Intent(this, RequestPrendaActivity.class);
        startActivity(intent);
    }
    public void Ir_Inventario(View view){
        Intent intent = new Intent(this, InventarioActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }
    public void Ir_Perfil(View view){
        if (usuario.equalsIgnoreCase("Ignacio")) {
            Intent intent1 = new Intent(this, IgnacioProfile.class);
            intent1.putExtra("userName", usuario);
            startActivity(intent1);
        } else if (usuario.equalsIgnoreCase("Nico")) {
            Intent intent2 = new Intent(this, NicoProfile.class);
            intent2.putExtra("userName", usuario);
            startActivity(intent2);
        } else if (usuario.equalsIgnoreCase("Pepo")) {
            Intent intent3 = new Intent(this, PepoProfile.class);
            intent3.putExtra("userName", usuario);
            startActivity(intent3);
        }
    }

    public void Ir_Pruebas(View view){
        Intent intent = new Intent(this, PruebasActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }
    public void Ir_Login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}