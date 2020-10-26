package com.jeradpas.calculator;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class AfficheMessage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private String unitStart;
    private String unitEnd;
    private Double result=0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_message);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Spinner spinner=findViewById(R.id.spinner);
        Spinner spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.units,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);

        // 6 - Configure all views

        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();


        final Button convertButton = (Button) findViewById(R.id.conv);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();}
        });

    }


    //--------CONFIGURATION DU NAVIGATION DRAWER--------------
    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

            switch (id){
            case R.id.db:
                lancerActi1();
                break;

        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }
    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void lancerActi1(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    //--------CONFIGURATION DES SPINNER--------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.spinner){
            unitStart=parent.getItemAtPosition(position).toString();
        }
        else{
            unitEnd=parent.getItemAtPosition(position).toString();
        }
    }

    public void convert(){
        TextView end = findViewById(R.id.end);
        EditText startText=findViewById(R.id.begin);
        String start=startText.getText().toString();
        System.out.println("Do you see me ?");
        System.out.println("Celsius ? "+unitStart.equals("Celsius"));
        if (unitStart.equals("Celsius")&unitEnd.equals("Kelvin")) {
            result = Double.parseDouble(start) + 273.15;
        }
        end.setText(result.toString());
        System.out.println(result);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

