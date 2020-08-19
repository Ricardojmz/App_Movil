package com.ecotesch.proy.Fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.L;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ecotesch.proy.MainActivity;
import com.ecotesch.proy.R;
import com.ecotesch.proy.registro;
import com.ecotesch.proy.test;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.getIntent;

public class MainFragment extends Fragment implements View.OnClickListener{

    //Objetos
    Button test,escaner;
    TextView c,co;
    EditText m;

    String correo,c4,fecha;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment,container,false);



        test = (Button) view.findViewById(R.id.test);
        escaner = (Button) view.findViewById(R.id.esca);
        c = (TextView) view.findViewById(R.id.codigo);
        //Programacion del oyente del boton de test
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
        //Programacion del oyente del boton de test
        escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escaner();
            }
        });
        return view;

    }

    public void facebook(View view) {
        Uri uri = Uri.parse("http://www.facebook.com/ecotesch.isc.3");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
        Toast.makeText(getContext(), "Abriendo Facebook",
                Toast.LENGTH_SHORT).show();
    }

    public void twitter(View view) {
        Uri uri = Uri.parse("http://www.twitter.com/ecotesch");
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent2);
        Toast.makeText(getContext(), "Abriendo Twitter",
                Toast.LENGTH_SHORT).show();
    }

    public void instagram(View view) {
        Uri uri = Uri.parse("http://www.instagram.com/Ecotesch_Isc/");
        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent3);
        Toast.makeText(getContext(), "Abriendo Instagram",
                Toast.LENGTH_SHORT).show();
    }

    public void youtube(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/channel/UCxXBvTgBdQaCjgrcT_7tfxA?view_as=subscriber");
        Intent intent4 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent4);
        Toast.makeText(getContext(), "Abriendo YouTube",
                Toast.LENGTH_SHORT).show();
    }




    //NO BORRAR ESTE APARTADO
    public MainFragment(){
    }

    //Funcion del metodo test
    public void test(){

        Intent test = new Intent(getContext(),com.ecotesch.proy.test.class);
        startActivity(test);
    }


    //Funcion del metodo escaner
    public void escaner(){

        Intent escanerb = new Intent(getContext(),com.ecotesch.proy.escaner_botella.class);
        startActivity(escanerb);
    }


    //NO BORRAR, NO BORRAR
    @Override
    public void onClick(View view) {
        test = (Button) view.findViewById(R.id.test);
        escaner = (Button) view.findViewById(R.id.esca);
        c = (TextView) view.findViewById(R.id.codigo);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
        escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escaner();
            }
        });
    }



}
