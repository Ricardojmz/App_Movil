package com.ecotesch.proy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class registro extends AppCompatActivity {

    Button btnreg;
    EditText etxnombre, etxuser, etxcorreo, etxpass;
    String nombrer, userr, emailr, passr, fecha;

    //para la emulacion de carga
    ProgressDialog progressDialog;

    //Para almacenar la cadena
    RequestQueue requestQueue;

    //Para la url
    String HttpURI = "http://192.168.1.70/ecotesch20/registro.php";

    Session session = null;
    String rec, subject, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnreg = (Button) findViewById(R.id.btnregistror);
        etxnombre = (EditText) findViewById(R.id.tilnombrer);
        etxuser = (EditText) findViewById(R.id.tilusuarior);
        etxcorreo = (EditText) findViewById(R.id.tilcorreor);
        etxpass = (EditText) findViewById(R.id.tilpassr);

        //Boton de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //inicializacion de RequestQueue
        requestQueue = Volley.newRequestQueue(registro.this);

        //inicaiizacion de prgressdialog
        progressDialog = new ProgressDialog(registro.this);


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });
    }


    //Accion del boton de back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    //Metodo de registrar
    public void Registrar() {
        //variables que obtendran de los campos
        nombrer = etxnombre.getText().toString();
        userr = etxuser.getText().toString();
        emailr = etxcorreo.getText().toString();
        passr = etxpass.getText().toString();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        fecha = df.format(c);

        //Validacion de campos vacios
        if (userr.isEmpty() || passr.isEmpty() || nombrer.isEmpty() || emailr.isEmpty())
            Toast.makeText(getApplicationContext(), "Debes introducir los cuatro campos", Toast.LENGTH_LONG).show();
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
                                    correo();
                                    Toast.makeText(getApplicationContext(), "Listo", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
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
                    Map<String, String> parametrosr = new HashMap<>();
                    parametrosr.put("nombrer", nombrer);
                    parametrosr.put("userr", userr);
                    parametrosr.put("emailr", emailr);
                    parametrosr.put("passr", passr);
                    parametrosr.put("fecha", fecha);
                    parametrosr.put("opcion", "registro");
                    return parametrosr;
                }
            };
            requestQueue.add(stringRequest);

        }
    }
    //Correo electronico
    public void correo(){
        rec = etxcorreo.getText().toString();
        subject = "Bienvenid@";
        textMessage = "Muchas gracias por formar parte de esta comunidad";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ecotesch@gmail.com", "Hola123*");
            }
        });

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}
