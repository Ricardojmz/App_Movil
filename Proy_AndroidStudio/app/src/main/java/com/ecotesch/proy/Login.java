package com.ecotesch.proy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ecotesch.proy.Fragments.MainFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnTouchListener{

    //Textview
    TextView txvreg;
    //Boton
    Button btnlog;
    //EditText
    EditText correo, contra;


    //para la emulacion de carga
    ProgressDialog progressDialog;

    //Para almacenar la cadena
    RequestQueue requestQueue;

    //Para la url
    String HttpURI = "http://192.168.1.70/ecotesch20/login.php";

    //Variables que alojaran los campos de la app
    String user;
    String pass;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Relacion
        txvreg = (TextView) findViewById(R.id.txvregistrolog);
        btnlog = (Button) findViewById(R.id.btningresarlog);
        correo = (EditText) findViewById(R.id.edtusuariolog);
        contra = (EditText) findViewById(R.id.edtpasslog);
        //Touch
        txvreg.setOnTouchListener(this);

        //inicializacion de RequestQueue
        requestQueue = Volley.newRequestQueue(Login.this);

        //inicaiizacion de prgressdialog
        progressDialog = new ProgressDialog(Login.this);

        //oyente del boton de ingresar
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ingresar();

            }
        });

    }



    //Funcion del metodo de Registro
    @Override
    public boolean onTouch(View view, MotionEvent m) {
        switch (m.getAction()){
            case MotionEvent.ACTION_DOWN:
                Intent registro = new Intent(getApplicationContext(),registro.class);
                startActivity(registro);
        }
        return true;
    }


    //Funcion del metodo de ingresar
    public void Ingresar(){
        //Obtengo valores
        user = correo.getText().toString();
        pass = contra.getText().toString();


        if (user.isEmpty() || pass.isEmpty())
            Toast.makeText(getApplicationContext(), "Debes llenar ambos campos", Toast.LENGTH_LONG).show();
        else {
            //Mostramos el progressDialog
            progressDialog.setMessage("Procesando...");
            progressDialog.show();

            //Creacion de la cadena a ejecutar en el web service mediante Volley
            StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String serverResponse) {
                            //Ocultamos el progressDialog
                            progressDialog.dismiss();

                            try {
                                JSONObject obj = new JSONObject(serverResponse);
                                Boolean error = obj.getBoolean("error");
                                String mensaje = obj.getString("mensaje");

                                if (error == true)
                                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                                else {

                                    Toast.makeText(getApplicationContext(), "Bienvenid@ " + user, Toast.LENGTH_LONG).show();
                                    Intent pagprin = new Intent(getApplicationContext(), MainActivity.class);
                                    pagprin.putExtra("usuario", user);
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
                            //Ocultamos el progressDialog
                            progressDialog.dismiss();

                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                protected Map<String, String> getParams() {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("user", user);
                    parametros.put("pass", pass);
                    parametros.put("opcion", "login");
                    return parametros;
                }
            };
            requestQueue.add(stringRequest);
        }


    }

}
