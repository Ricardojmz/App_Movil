package com.ecotesch.proy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ecoweb extends AppCompatActivity {

    Button esca;
    TextView mos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecoweb);

        //Boton de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        esca = (Button) findViewById(R.id.btnescanerweb);

        mos = (TextView) findViewById(R.id.mostrar);

        esca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escanerweb();
            }
        });
    }

    //Accion del boton de back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Metodo escanear web
    public void escanerweb(){
        IntentIntegrator intentIntegrator = new IntentIntegrator(ecoweb.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
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

        if(result != null)
            if(result.getContents() != null){
                mos.setText(result.getContents().toString());
            }else{
                Toast.makeText(getApplicationContext(),"Error al escaner el codigo", Toast.LENGTH_SHORT).show();
            }
    }
}
