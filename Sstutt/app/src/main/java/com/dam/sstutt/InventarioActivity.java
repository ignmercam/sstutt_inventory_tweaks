package com.dam.sstutt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONObject;

import android.widget.ListView;

import com.android.volley.RequestQueue;



public class InventarioActivity extends AppCompatActivity {

    public static String usuario;
    private RequestQueue mQueue;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        usuario = getIntent().getStringExtra("userName");

        mQueue = Volley.newRequestQueue(this);
        //mTextView = findViewById(R.id.textView);
        mListView = findViewById(R.id.listPrendas);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://sstutt.loca.lt/listPrendas",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesa la respuesta JSON y muestra los resultados en el ListView

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject post = response.getJSONObject(i);

                                String item = "MODELO: " + post.getString("modelo") + "\n" +
                                        "TALLA: " + post.getString("talla") + "\n" +
                                        "CANTIDAD: " + post.getString("cantidad") + "\n\n";
                                mAdapter.add(item);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Maneja los errores de la solicitud

                    }
                }
        );

        mQueue.add(jsonArrayRequest);
    }

    public void Ir_Menu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }

    public void Ir_AñadirPrenda(View view){
        Intent intent = new Intent(this, ClothingFormActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }

    public void Ir_SolicitarPrenda(View view){
        Intent intent = new Intent(this, RequestPrendaActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }
        public void Ir_AñadirModelo(View view){
        Intent intent = new Intent(this, ModeloActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }
}