package com.dam.sstutt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class PruebasActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private TextView mTextView;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    public static String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);
        usuario = getIntent().getStringExtra("userName");

        mQueue = Volley.newRequestQueue(this);
        //mTextView = findViewById(R.id.textView);
        mListView = findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://sstutt.loca.lt/listUsuarios",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesa la respuesta JSON y muestra los resultados en el ListView

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject post = response.getJSONObject(i);
                                /*String item = "ID: " + post.getInt("id") + "\n" +
                                        "Título: " + post.getString("title") + "\n" +
                                        "Cuerpo: " + post.getString("body") + "\n\n";*/
                                String item = "ID: " + post.getString("id") + "\n" +
                                        "USUARIO: " + post.getString("nombre") + "\n" +
                                        "EMAIL: " + post.getString("email") + "\n\n";
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
                        mTextView.setText("Error: " + error.getMessage());
                    }
                }
        );

        mQueue.add(jsonArrayRequest);
    }
    public void Ir_Menu(View view){
        Intent intent = new Intent(this, MainRootActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }
}