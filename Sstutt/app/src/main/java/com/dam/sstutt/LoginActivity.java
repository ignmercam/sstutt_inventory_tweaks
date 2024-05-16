package com.dam.sstutt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.equalsIgnoreCase("Ignacio") && password.equals("dam")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                } else if (username.equalsIgnoreCase("Nico") && password.equals("dam")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                } else if (username.equalsIgnoreCase("Pepo") && password.equals("dam")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                    //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else if (username.equalsIgnoreCase("Admin") && password.equals("admin")){
                    Intent intent = new Intent(LoginActivity.this, MainRootActivity.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}