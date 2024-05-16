package com.dam.sstutt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestPrendaActivity extends AppCompatActivity {

    private EditText reqModelo;
    private EditText reqTalla;
    private TextView resModelo;
    private  TextView resTalla;
    private TextView resCantidad;
    private ImageView resPrendaImg;
    private Button botonSolicitar;
    public static String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.req_prenda);
        usuario = getIntent().getStringExtra("userName");

        botonSolicitar = (Button) findViewById(R.id.btSolicitar);

        botonSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hacer_peticion_REST(view);
            }
        });


    }

    public void Hacer_peticion_REST (View view) {

        reqModelo= (EditText)findViewById(R.id.textoModelo);
        reqTalla= (EditText)findViewById(R.id.textoTalla);

        String modelo = reqModelo.getText().toString();
        String talla = reqTalla.getText().toString();

        resModelo = (TextView)findViewById(R.id.resModelo);
        resTalla = (TextView)findViewById(R.id.resTalla);
        resCantidad = (TextView)findViewById(R.id.resCantidad);
       // resPrendaImg = findViewById(R.id.imagenPrenda);

        final String URL="https://sstutt.loca.lt/reqPrenda?modelo=" + modelo + "&talla=" + talla;
        final ProgressDialog dlg = ProgressDialog.show(
                this,
                "Obteniendo los datos REST",
                "Por favor, espere...", true);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        dlg.dismiss();
                        try{
                            String modeloPrenda = response.getString("modelo");
                            String tallaPrenda = response.getString("talla");
                            String cantidadPrenda = response.getString("cantidad");
                            //Se ha recibido una previsión
                            Toast.makeText(getApplicationContext(),
                                    "Prenda recibida",
                                    Toast.LENGTH_SHORT).show();
                            resModelo.setText("Modelo: " + modeloPrenda);
                            resTalla.setText("Talla:" + tallaPrenda);
                            resCantidad.setText("Cantidad: " + cantidadPrenda);

                            //String nombreImagen = "R.drawable." + modeloPrenda.toLowerCase();
                           // Drawable drawable = getResources().getDrawable(Integer.parseInt(nombreImagen));

                            // Establezca el src del ImageView
                            //resPrendaImg.setImageDrawable(drawable);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        VolleyLog.v("Response:%n %s", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dlg.dismiss();
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "No se ha recibido la prenda", Toast.LENGTH_SHORT).show();
            }
        });
        // add the request object to the queue to be executed
        SstuttApplication.getInstance().getRequestQueue().add(request);
    }

    public void Ir_Inventario(View view){
        Intent intent = new Intent(this, InventarioActivity.class);
        intent.putExtra("userName", usuario);
        startActivity(intent);
    }



}
