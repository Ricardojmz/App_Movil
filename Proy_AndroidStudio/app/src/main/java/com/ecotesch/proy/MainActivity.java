 package com.ecotesch.proy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

import com.ecotesch.proy.Fragments.FragmentPersonas;
import com.ecotesch.proy.Fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

 public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    //Variables para cargar el fragment principal
     FragmentManager fragmentManager;
     FragmentTransaction fragmentTransaction;



     //Variable que almacena el correo
     String correos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo un Intent el cual me va a traer el valor del correo ingresado en el login
        Intent correoc = getIntent();
        correos = correoc.getStringExtra("usuario");



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        //Establecer evento onclick al navigation
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //Cargar fragment principal
        fragmentManager =  getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();

        crearShare();

    }


    //Aqui se programa la parte a la que se va a mandar en caso de que seleccione
     //Una parte del menu
     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        //Opcion Logros del menu
        if(item.getItemId() == R.id.logros){
            Intent cuenta = new Intent(getApplicationContext(), cuenta.class);
            cuenta.putExtra("usuario", correos);
            startActivity(cuenta);
        }
        //Opcion de contacto
         if(item.getItemId() == R.id.contacto){
             Intent contact = new Intent(getApplicationContext(), patrocinador.class);
             startActivity(contact);
         }
         //Opcion de minijuego
         if(item.getItemId() == R.id.minijuego){

         }
         //Opcion de ecoteschweb
         if(item.getItemId() == R.id.ecoteschweb){
            Intent ecow = new Intent(getApplicationContext(), ecoweb.class);
            startActivity(ecow);
         }
         //Opcion de salir
         if(item.getItemId() == R.id.salir){
             Intent cerrarsesion = new Intent(getApplicationContext(),Login.class);
             startActivity(cerrarsesion);
             finish();
         }


        return false;
     }

     private void crearShare() {
         SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = prefs.edit();
         editor.putString("correo", correos);
         editor.commit();
     }


 }
