package com.jeradpas.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button btn0 = (Button) findViewById(R.id.b0);
        final Button btn1 = (Button) findViewById(R.id.b1);
        final Button btn2 = (Button) findViewById(R.id.b2);
        final Button btn3 = (Button) findViewById(R.id.b3);
        final Button btn4 = (Button) findViewById(R.id.b4);
        final Button btn5 = (Button) findViewById(R.id.b5);
        final Button btn6 = (Button) findViewById(R.id.b6);
        final Button btn7 = (Button) findViewById(R.id.b7);
        final Button btn8 = (Button) findViewById(R.id.b8);
        final Button btn9 = (Button) findViewById(R.id.b9);

        final Button bp = (Button) findViewById(R.id.b_plus);
        final Button be = (Button) findViewById(R.id.b_equal);
        final Button bm = (Button) findViewById(R.id.b_moins);
        final Button bx = (Button) findViewById(R.id.b_times);
        final Button bv = (Button) findViewById(R.id.b_coma);
        final Button bdel = (Button) findViewById(R.id.b_delete);
        final Button bclear= (Button) findViewById(R.id.b_clear);

        Button[] buttons={btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,bp,be,bm,bx,bv,bdel,bclear};

        for (final Button btn: buttons){
        btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (v==findViewById(R.id.b_delete)) erase(v);
               else if (v==findViewById(R.id.b_clear)) clearText(v);
               else if (v==findViewById(R.id.b_equal)) updateResult(v);
               else updateCalculus(v,btn);
            }
       });
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearText(View v) {
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText("");
    }

    private void updateResult(View v) {
        EditText editText = (EditText) findViewById(R.id.editText);
        Double result;
        result = calcul(v);
        editText.setText(result.toString());
    }

    private Double calcul(View v) {
        Double result= Double.valueOf(0);
        EditText editText = (EditText) findViewById(R.id.editText);
        String str=editText.getText().toString();
        List<String> list=analyse(str);
        List<String> operands=new ArrayList<>();
        List<Double> numbers=new ArrayList<>();

        for (int i=0;i<list.size();i++){
            if(i%2==0){
                numbers.add(Double.parseDouble(list.get(i)));
            }
            else operands.add(list.get(i));
        }

        System.out.println(operands);
        System.out.println(numbers);
        List<Double> ghost=new ArrayList<>();
        ghost.addAll(numbers);
        int index;
        List<Integer> indexes=new ArrayList<>();


            for (String op:operands){

                System.out.println("Etape 1");
                if (op.equals("x")){
                    System.out.println("Je vois un x");
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
            for(int i:indexes){
                System.out.println("Supprime moi!!! "+i);
                operands.remove(i);
            }
            System.out.println("Avancement calcul"+ghost);
            System.out.println("Calculs restants"+operands);

            indexes.clear();
            for (String op:operands){

                System.out.println("Etape 2");
                if (op.equals("/")){
                    System.out.println("Je vois un x");
                    index=operands.indexOf(op);
                    indexes.add(index);
                    double x=ghost.get(index);
                    System.out.println("X " +x);
                    System.out.println("Index "+index);
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
                    System.out.println("Je vois un -");
                    index=operands.indexOf(op);
                    indexes.add(index);
                    double x=ghost.get(index);
                    System.out.println("X " +x);
                    System.out.println("Index "+index);
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
                    System.out.println("Je vois un +");
                    index=operands.indexOf(op);
                    indexes.add(index);
                    double x=ghost.get(index);
                    System.out.println("X " +x);
                    System.out.println("Index "+index);
                    double y=ghost.get(index+1);
                    ghost.remove(index);
                    ghost.remove(index);
                    ghost.add(index,x+y);

                }
            }
            for(int i:indexes) operands.remove(i);


        result=ghost.get(0);
        return result;
    }

    public void updateCalculus(View v,Button btn) {
        EditText editText = (EditText) findViewById(R.id.editText);
        if (btn.getText().toString().equals(",")){
            editText.setText(editText.getText()+".");
        }
        else {
            editText.setText(editText.getText()+btn.getText().toString());
        }
    }

    public void erase(View v){
        EditText editText = (EditText) findViewById(R.id.editText);
        int length = editText.getText().length();
        if (length>0) {
            editText.setText(editText.getText().delete(length - 1, length));
        }
    }

    public List<String> analyse(String string) {
        int length = string.length();
        List<String> text = new ArrayList<>();
        List<String> ope = new ArrayList<>();
        ope.add("+");
        ope.add("-");
        ope.add("x");
        ope.add("/");
        int previous = 0;

        for (int i = 0; i < length; i++) {

            if (ope.contains(string.substring(i, i + 1))) {
                text.add(string.substring(previous, i));
                text.add(string.substring(i, i + 1));
                previous = i + 1;
            }
           }
        text.add(string.substring(previous, length));

//        System.out.println("this is string " + string);
//        System.out.println("Is it good ? " + text);


        return text;

    }}
