package com.dam.sstutt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ClothingFormActivity extends AppCompatActivity {

    public static String usuario;
    private Button botonAñadir;
    private Button botonEliminar;
    private EditText reqModelo;
    private EditText reqTalla;
    private EditText reqCantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_form);
        usuario = getIntent().getStringExtra("userName");
        botonAñadir = (Button) findViewById(R.id.btAñadir);

        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Añadir_PUT(view);
            }
        });
        botonEliminar = (Button) findViewById(R.id.btEliminar);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar_PUT(view);
            }
        });
    }

    public void Ir_Inventario(View view){
        Intent intent = new Intent(this, InventarioActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }

    public void Añadir_PUT (View view) {

        reqModelo= (EditText)findViewById(R.id.txtModelo);
        reqTalla= (EditText)findViewById(R.id.txtTalla);
        reqCantidad= (EditText)findViewById(R.id.txtCantidad);

        String modelo = reqModelo.getText().toString();
        String talla = reqTalla.getText().toString();
        String cantiad = reqCantidad.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("modelo", modelo);
            jsonObject.put("talla", talla);
            jsonObject.put("cantidad", cantiad);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final String URL="https://sstutt.loca.lt/plusPrenda?modelo=" + modelo + "&talla=" + talla;
        final ProgressDialog dlg = ProgressDialog.show(
                this,
                "Enviando datos",
                "Por favor, espere...", true);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,URL, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dlg.dismiss();
                        //Toast.makeText(getApplicationContext(), "Unidad/es añadidas correctamente", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dlg.dismiss();
                //VolleyLog.e("Error: ", error.getMessage());
                //Toast.makeText(getApplicationContext(),
                       // "No se han añadido las unidades", Toast.LENGTH_SHORT).show();
            }
        });
        // add the request object to the queue to be executed
        SstuttApplication.getInstance().getRequestQueue().add(request);
    }

    public void Eliminar_PUT (View view) {

        reqModelo= (EditText)findViewById(R.id.txtModelo);
        reqTalla= (EditText)findViewById(R.id.txtTalla);
        reqCantidad= (EditText)findViewById(R.id.txtCantidad);

        String modelo = reqModelo.getText().toString();
        String talla = reqTalla.getText().toString();
        String cantiad = reqCantidad.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("modelo", modelo);
            jsonObject.put("talla", talla);
            jsonObject.put("cantidad", cantiad);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final String URL="https://sstutt.loca.lt/minusPrenda?modelo=" + modelo + "&talla=" + talla;
        final ProgressDialog dlg = ProgressDialog.show(
                this,
                "Enviando datos",
                "Por favor, espere...", true);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,URL, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dlg.dismiss();
                        //Toast.makeText(getApplicationContext(), "Unidad/es eliminadas correctamente", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dlg.dismiss();
                //VolleyLog.e("Error: ", error.getMessage());
                //Toast.makeText(getApplicationContext(),
                       // "No se han eliminado las unidades", Toast.LENGTH_SHORT).show();
            }
        });
        // add the request object to the queue to be executed
        SstuttApplication.getInstance().getRequestQueue().add(request);
    }
}