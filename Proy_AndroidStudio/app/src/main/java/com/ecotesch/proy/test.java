package com.ecotesch.proy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class test extends AppCompatActivity {

    TextView txtpres, txtpre1, txtpre2, txtpre3, txtpre4;
    RadioButton r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
    Button btnrespuesta;
    String pregunta1 = "1. ¿De qué color es el contenedor para depositar PET?";
    String pregunta2 = "2. ¿El plástico es un residuo inorgánico?";
    String pregunta3 = "3. ¿Una cascara de plátano es basura de tipo?";
    String pregunta4 = "4. ¿Reutilizar entra en las tres R?";
    String resu, re2, re3, preguntas;
    int correctas,incorrectas, res;
    //Para almacenar la cadena
    RequestQueue requestQueue;

    String correo_e;

    //Para la url
    String HttpURI = "http://192.168.1.70/ecotesch20/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        txtpres = (TextView) findViewById(R.id.correo);


        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        correo_e = prefs.getString("correo", "");
        //txtpres.setText(correo_e);




        //Boton de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtpre1 = (TextView) findViewById(R.id.txvpregunta1);
        txtpre1.setText(pregunta1);

        txtpre2 = (TextView) findViewById(R.id.txvpregunta2);
        txtpre2.setText(pregunta2);

        txtpre3 = (TextView) findViewById(R.id.txvpregunta3);
        txtpre3.setText(pregunta3);

        txtpre4 = (TextView) findViewById(R.id.txvpregunta4);
        txtpre4.setText(pregunta4);

        btnrespuesta = (Button) findViewById(R.id.btnrespuesta);

        //inicializacion de RequestQueue
        requestQueue = Volley.newRequestQueue(test.this);



        r1 = (RadioButton) findViewById(R.id.rbOpcion1);
        r2 = (RadioButton) findViewById(R.id.rbOpcion2);
        r3 = (RadioButton) findViewById(R.id.rbOpcion3);
        r4 = (RadioButton) findViewById(R.id.rbOpcion21);
        r5 = (RadioButton) findViewById(R.id.rbOpcion22);
        r6 = (RadioButton) findViewById(R.id.rbOpcion31);
        r7 = (RadioButton) findViewById(R.id.rbOpcion32);
        r8 = (RadioButton) findViewById(R.id.rbOpcion33);
        r9 = (RadioButton) findViewById(R.id.rbOpcion41);
        r10 = (RadioButton) findViewById(R.id.rbOpcion42);

        btnrespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta();
            }
        });
    }
    //Accion del boton de back
    @Override
    public boolean onSupportNavigateUp() {
        Intent pagprin = new Intent(getApplicationContext(),MainActivity.class);
        pagprin.putExtra("usuario", correo_e);
        startActivity(pagprin);
        //onBackPressed();
        return true;
    }


    private void respuesta(){
        if (r1.isChecked()) {
            incorrectas++;
        }
        if (r2.isChecked()) {
            correctas++;
            preguntas = "1";
        }
        if (r3.isChecked()) {
            incorrectas++;

        }
        if (r4.isChecked()) {
            correctas++;
            preguntas = "1,2";
        }
        if (r5.isChecked()) {
            incorrectas++;

        }
        if (r6.isChecked()) {
            incorrectas++;

        }
        if (r7.isChecked()) {
            incorrectas++;

        }
        if (r8.isChecked()) {
            correctas++;
            preguntas = "1,2,3";
        }
        if (r9.isChecked()){
            correctas++;
            preguntas = "1,2,3,4";
        }
        if (r10.isChecked()){
            incorrectas++;

        }
        if (r1.isChecked() == false && r2.isChecked() == false && r3.isChecked() == false || r4.isChecked() == false && r5.isChecked() == false ||
                r6.isChecked() == false && r7.isChecked() == false && r8.isChecked() == false || r9.isChecked() == false && r10.isChecked() == false){
            correctas = 0;
            incorrectas = 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(test.this);
            builder.setTitle("Recordatorio");
            builder.setMessage("Por favor contesta las preguntas")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Si es que da si en el cuadro de dialogo
                            /*Intent ques = new Intent(getApplicationContext(), Quest.class);
                            startActivity(ques);*/
                        }
                    })
                    .setCancelable(false)
                    .show();

        }else {
            Intent recibir = getIntent();
            final String usuario = recibir.getStringExtra("usuario");
            AlertDialog.Builder builder = new AlertDialog.Builder(test.this);
            builder.setTitle("Resultado");
            builder.setMessage("El resultado de tu test.\n"
                    + "Respuestas correctas: " + correctas + "\n"
                    + "Respuestas incorrectas: " + incorrectas)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Si es que da si en el cuadro de dialogo
                            resu = String.valueOf(correctas);
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
                                                    //AQUI VA EL INTENT DEL RA A RECIBIR

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
                                    parametros.put("usuario",correo_e);
                                    parametros.put("resu",resu);
                                    parametros.put("opcion", "resultadotest");
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
}
