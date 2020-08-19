package com.ecotesch.proy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class patrocinador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrocinador);

        //Boton de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //Accion del boton de back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    public void facebook(View view) {
        Uri uri = Uri.parse("http://www.facebook.com/ecotesch.isc.3");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(), "Abriendo Facebook",
                Toast.LENGTH_SHORT).show();
    }

    public void twitter(View view) {
        Uri uri = Uri.parse("http://www.twitter.com/ecotesch");
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent2);
        Toast.makeText(getApplicationContext(), "Abriendo Twitter",
                Toast.LENGTH_SHORT).show();
    }

    public void instagram(View view) {
        Uri uri = Uri.parse("http://www.instagram.com/Ecotesch_Isc/");
        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent3);
        Toast.makeText(getApplicationContext(), "Abriendo Instagram",
                Toast.LENGTH_SHORT).show();
    }

    public void youtube(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/channel/UCxXBvTgBdQaCjgrcT_7tfxA?view_as=subscriber");
        Intent intent4 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent4);
        Toast.makeText(getApplicationContext(), "Abriendo YouTube",
                Toast.LENGTH_SHORT).show();
    }
}
