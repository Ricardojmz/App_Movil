package com.ecotesch.proy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ecotesch.proy.Fragments.MainFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class escaner_botella extends AppCompatActivity {

    TextView gg;
    String fecha,cb,correo_e;
    int pun = 1;
    String punto = String.valueOf(pun);

    //Para almacenar la cadena
    RequestQueue requestQueue;

    //Para la url
    String HttpURI = "http://192.168.1.70/ecotesch20/producto.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner_botella);

        gg = (TextView) findViewById(R.id.codigohhhh);

        escanerweb();
        //inicializacion de RequestQueue
        requestQueue = Volley.newRequestQueue(escaner_botella.this);



    }

    //Metodo escanear web
    public void escanerweb(){
        IntentIntegrator intentIntegrator = new IntentIntegrator(escaner_botella.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Escaner codigo");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();
    }


    //Activar camara
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        fecha = df.format(c);
        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        correo_e = prefs.getString("correo", "");
        if(result != null)
            if(result.getContents() != null){
                //gg.setText(result.getContents().toString());
                cb = result.getContents();
                //Creacion de la cadena a ejecutar en el web service mediante Volley
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURI,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String serverResponse) {
                                try {
                                    JSONObject obj = new JSONObject(serverResponse);
                                    Boolean error = obj.getBoolean("error");
                                    String mensaje = obj.getString("mensaje");

                                    if (error == true)
                                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                                    else {
                                        punto();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> parametros = new HashMap<>();
                        parametros.put("cb", cb);
                        parametros.put("usuario",correo_e);
                        parametros.put("fecha", fecha);
                        parametros.put("opcion", "producto");
                        return parametros;
                    }
                };
                requestQueue.add(stringRequest);
            }else{
                Toast.makeText(getApplicationContext(),"Error al escaner el codigo", Toast.LENGTH_SHORT).show();
            }
    }



    private void punto(){
        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        correo_e = prefs.getString("correo", "");
        AlertDialog.Builder builder = new AlertDialog.Builder(escaner_botella.this);
        builder.setTitle("Punto");
        builder.setMessage("Gracias por registrar la botella.\n"
                + "Pasaras a contestar el cuestionario\n"
                + "para conseguir un premio")
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Si es que da si en el cuadro de dialogo
                        Intent quest = new Intent(getApplicationContext(),test.class);
                        startActivity(quest);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    //Si es que da que no al dialogo
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Creacion de la cadena a ejecutar en el web service mediante Volley
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURI,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String serverResponse) {
                                        try {
                                            JSONObject obj = new JSONObject(serverResponse);
                                            Boolean error = obj.getBoolean("error");
                                            String mensaje = obj.getString("mensaje");

                                            if (error == true)
                                                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                                            else {
                                                Intent pagprin = new Intent(getApplicationContext(), MainActivity.class);
                                                pagprin.putExtra("usuario", correo_e);
                                                startActivity(pagprin);
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                            protected Map<String, String> getParams() {
                                Map<String, String> parametros = new HashMap<>();
                                parametros.put("punto", punto);
                                parametros.put("usuario", correo_e);
                                parametros.put("opcion", "punto");
                                return parametros;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
