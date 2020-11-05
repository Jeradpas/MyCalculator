package com.jeradpas.calculator;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.material.navigation.NavigationView;

public class AfficheMessage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_message);

        mDrawerLayout =findViewById(R.id.drawer_layout2);


        final Button b_long = findViewById(R.id.length);
        final Button b_vol = findViewById(R.id.volume);
        final Button b_aire = findViewById(R.id.aire);
        final Button b_masse = findViewById(R.id.masse);
        final Button b_temp = findViewById(R.id.temperature);
        final Button b_devi = findViewById(R.id.devise);

        Button[] buttons2={b_long, b_vol, b_aire, b_masse, b_temp, b_devi};


        for (final Button btn: buttons2){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lancerActi(btn);
                }
            });
        }
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    // --------------
    // Lancement des differentes activites
    // --------------

    @SuppressLint("NonConstantResourceId")
    private void lancerActi(Button btn) {
        int id = btn.getId();
        switch (id) {
            case R.id.length:
                Intent intent = new Intent(this, LengthConverter.class);
                startActivity(intent);
                break;
            case R.id.volume:
                Intent intent2 = new Intent(this, SurfaceConverter.class);
                startActivity(intent2);
                break;
            case R.id.aire:
                Intent intent3 = new Intent(this, VolumeConverter.class);
                startActivity(intent3);
                break;
            case R.id.masse:
                Intent intent4 = new Intent(this, MasseConverter.class);
                startActivity(intent4);
                break;
            case R.id.temperature:
                Intent intent5 = new Intent(this, TemperatureConverter.class);
                startActivity(intent5);
                break;
            case R.id.devise:
                Intent intent6 = new Intent(this, CurrencyConverter.class);
                startActivity(intent6);
                break;



        }
    }

    @Override
    public void onBackPressed() {
        // 5 - Gestion du "hamburger"
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // --------------
    // // Gestion des item du Navigation Drawer
    // --------------

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case (R.id.db):
                lancerActi1();
                break;
            case (R.id.imc):
                lancerImc();
                break;
            case (R.id.logout):
                finish();
                System.exit(0);

        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void lancerActi1(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void lancerImc(){
        Intent intent= new Intent(this,Imc.class);
        startActivity(intent);
    }


    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = findViewById(R.id.activity_message_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.mDrawerLayout = findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        NavigationView navigationView = findViewById(R.id.activity_message_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
