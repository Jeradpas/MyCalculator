package com.jeradpas.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        final Button bp = (Button) findViewById(R.id.bp);
        final Button be = (Button) findViewById(R.id.be);
        final Button bm = (Button) findViewById(R.id.bm);
        final Button bx = (Button) findViewById(R.id.bx);
        final Button bv = (Button) findViewById(R.id.bv);
        final Button bdel = (Button) findViewById(R.id.bdel);

        Button[] buttons={btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,bp,be,bm,bx,bv,bdel};

        for (final Button btn: buttons){
        btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (v==findViewById(R.id.bdel)) erase(v);
               else if (v==findViewById(R.id.be)) updateResult(v);
               else updateCalculus(v,btn);
            }
       });
        }

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
        analyse(str);
        return result;
    }

    public void updateCalculus(View v,Button btn) {
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(editText.getText()+btn.getText().toString());
    }

    public void erase(View v){
        EditText editText = (EditText) findViewById(R.id.editText);
        int length = editText.getText().length();
        editText.setText(editText.getText().delete(length - 1, length));
    }

    public List<String> analyse(String string){
        int length=string.length();
        List<String[]> text=new ArrayList<>();
        System.out.println("this is string "+string);
        String[] split = string.split("[-x/+]");

        for( String i:split){
            System.out.println("this is split "+i);
        }

        return Arrays.asList(split);
    }

    }
