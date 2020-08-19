package com.ecotesch.proy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cuenta extends AppCompatActivity {

    //Variables
    TextView correo,agra;

    int contador = 0;

    //Para almacenar la cadena
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        //Boton de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Relacion del elemento
        correo = (TextView) findViewById(R.id.correocuenta);
        Intent usuario = getIntent();
        correo.setText(usuario.getStringExtra("usuario"));
        agra = (TextView) findViewById(R.id.textagra);


        //inicializacion de RequestQueue
        requestQueue = Volley.newRequestQueue(cuenta.this);

        Estadistica("http://192.168.1.70/ecotesch20/Estadistica.php?usuario="+usuario.getStringExtra("usuario")+"");
    }
    //Accion del boton de back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void Estadistica(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        jsonObject.getString("cantidad");
                        contador++;

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                agra.setText("Gracias por reciclar "+contador+" botellas. Tal vez parezca mucho"
                        + " o tal vez parezca poco, pero tuviste la iniciativa y eso se agradece. Te invitamos a que sigas con esa"+
                        " actitud de ayudar a disminuir los índices de contaminación.");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);

    }
}
