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
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    List<String> operands=new ArrayList<>();
    List<Double> numbers=new ArrayList<>();
    List<String> ope = new ArrayList<>();
    List<String> num = new ArrayList<>();
    String temp = "";
    double result=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout =findViewById(R.id.drawer_layout);


        ope.add("+");
        ope.add("-");
        ope.add("x");
        ope.add("/");
        ope.add("(");
        ope.add(")");

        num.add("0");
        num.add("1");
        num.add("2");
        num.add("3");
        num.add("4");
        num.add("5");
        num.add("6");
        num.add("7");
        num.add("8");
        num.add("9");


        final Button btn0 =  findViewById(R.id.b0);
        final Button btn1 =  findViewById(R.id.b1);
        final Button btn2 =  findViewById(R.id.b2);
        final Button btn3 =  findViewById(R.id.b3);
        final Button btn4 =  findViewById(R.id.b4);
        final Button btn5 =  findViewById(R.id.b5);
        final Button btn6 =  findViewById(R.id.b6);
        final Button btn7 =  findViewById(R.id.b7);
        final Button btn8 =  findViewById(R.id.b8);
        final Button btn9 =  findViewById(R.id.b9);

        final Button bp =  findViewById(R.id.b_plus);
        final Button be =  findViewById(R.id.b_equal);
        final Button bm =  findViewById(R.id.b_moins);
        final Button bx =  findViewById(R.id.b_times);
        final Button bd =  findViewById(R.id.b_divide);
        final Button bv =  findViewById(R.id.b_coma);
        final Button bdel =  findViewById(R.id.b_delete);
        final Button bs = findViewById(R.id.b_signe);
        final Button bclear=  findViewById(R.id.b_clear);

        Button[] buttons={btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,bp,be,bd,bm,bx,bv,bdel,bclear,bs};

        for (final Button btn: buttons){
        btn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                    if (v==findViewById(R.id.b_delete)) erase(v);
                    else if (v==findViewById(R.id.b_clear)) clearText();
//                 else if (v==findViewById(R.id.b_equal)) updateResult();
                    else updateCalculus(btn);
            }
       });
        }



        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
}

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
            case (R.id.converter):
                lancerActi2();
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

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        NavigationView navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void lancerActi2(){
        Intent intent= new Intent(this,AfficheMessage.class);
        startActivity(intent);
    }

    private void lancerImc(){
        Intent intent= new Intent(this,Imc.class);
        startActivity(intent);
    }



    // --------------------
    // CALCULATRICE
    // --------------------


    private void clearText() {
        EditText editText = findViewById(R.id.editText);
        editText.setText("");
        numbers.clear();
        operands.clear();
    }


    @SuppressLint("SetTextI18n")
    public void updateCalculus(Button btn) {
        EditText editText = findViewById(R.id.editText);
        String newInput = btn.getText().toString();
        

            System.out.println(newInput);

            if (ope.contains(newInput)&&!temp.equals("")){
                try{
                    System.out.println("Je suis un opérateur");
                    editText.setText(editText.getText() + btn.getText().toString());
                    operands.add(newInput);
                    numbers.add(Double.parseDouble(temp));
                    temp="";
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Requete impossible", Toast.LENGTH_LONG).show();
                }

            } else if (newInput.equals(",")) {
                System.out.println("Ajout d'une ,");
                temp = temp+".";
                editText.setText(editText.getText() + ".");
            } else if (newInput.equals("( - )")&& temp.equals("")) {
                System.out.println("Ajout d'un signe");
                temp = temp + "-";
                editText.setText(editText.getText() + "-");
            }else if(num.contains(newInput)) {
                System.out.println("Ajout d'un nombre");
                temp = temp + newInput;
                editText.setText(editText.getText() + newInput);
            }
            else if (newInput.equals("=")){
                try{
                    numbers.add(Double.parseDouble(temp));
                    temp="";
                    System.out.println(numbers);
                    System.out.println(operands);
                    List<Double> ghost = new ArrayList<>(numbers);
                    int index;
                    List<Integer> indexes=new ArrayList<>();


                    for (String op:operands){

                        System.out.println("Etape 1");
                        if (op.equals("x")){
                            index=operands.indexOf(op);
                            indexes.add(index);
                            double x=ghost.get(index);
                            System.out.println("X " +x);
                            System.out.println("Index "+index);
                            double y=ghost.get(index+1);
                            ghost.remove(index);
                            ghost.remove(index);
                            ghost.add(index,x*y);

                        }
                    }
                    for(int i:indexes) operands.remove(i);


                    indexes.clear();
                    for (String op:operands){

                        System.out.println("Etape 2");
                        if (op.equals("/")){
                            index=operands.indexOf(op);
                            indexes.add(index);
                            double x=ghost.get(index);
                            double y=ghost.get(index+1);
                            ghost.remove(index);
                            ghost.remove(index);
                            ghost.add(index,x/y);

                        }
                    }
                    for(int i:indexes) operands.remove(i);

                    indexes.clear();
                    for (String op:operands){

                        System.out.println("Etape 3");
                        if (op.equals("-")){

                            index=operands.indexOf(op);
                            indexes.add(index);
                            double x=ghost.get(index);
                            double y=ghost.get(index+1);
                            ghost.remove(index);
                            ghost.remove(index);
                            ghost.add(index,x-y);

                        }
                    }
                    for(int i:indexes) operands.remove(i);

                    indexes.clear();
                    for (String op:operands){

                        System.out.println("Etape 4");
                        if (op.equals("+")){

                            index=operands.indexOf(op);
                            indexes.add(index);
                            double x=ghost.get(index);
                            double y=ghost.get(index+1);
                            ghost.remove(index);
                            ghost.remove(index);
                            ghost.add(index,x+y);

                        }
                    }
                    for(int i:indexes) operands.remove(i);

                    result=ghost.get(0);
                    editText.setText(String.valueOf(result));


                }catch(Exception e){
                Toast.makeText(getApplicationContext(), "Requete impossible", Toast.LENGTH_LONG).show();
                }
                
            }else{
                Toast.makeText(getApplicationContext(), "Requete impossible", Toast.LENGTH_LONG).show();
                
            }

    }

    public void erase(View v){
        EditText editText = findViewById(R.id.editText);
        int length = editText.getText().length();
        if (length>0) {
            temp=temp.substring(0,temp.length()-1);
            editText.setText(editText.getText().delete(length - 1, length));
        }
    }

}
