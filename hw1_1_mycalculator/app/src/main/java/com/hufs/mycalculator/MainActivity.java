package com.hufs.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText text;
    private Button n1,n2,n3,n4,n5,n6,n7,n8,n9,n0,plus,minus,multiply,clear,division, result;
    private String getText;
    private ArrayList<String> arr = new ArrayList<String>();
    private String tmp1 = "", tmp2 = "";
    private int itmp1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeView();
        this.setListener();
    }

    public void setListener(){
        n1.setOnClickListener(this);
        n2.setOnClickListener(this);
        n3.setOnClickListener(this);
        n4.setOnClickListener(this);
        n5.setOnClickListener(this);
        n6.setOnClickListener(this);
        n7.setOnClickListener(this);
        n8.setOnClickListener(this);
        n9.setOnClickListener(this);
        n0.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        clear.setOnClickListener(this);
        division.setOnClickListener(this);
        multiply.setOnClickListener(this);
        result.setOnClickListener(this);
    }
    public void initializeView(){
        n1 = (Button)findViewById(R.id.n1);
        n2 = (Button)findViewById(R.id.n2);
        n3 = (Button)findViewById(R.id.n3);
        n4 = (Button)findViewById(R.id.n4);
        n5 = (Button)findViewById(R.id.n5);
        n6 = (Button)findViewById(R.id.n6);
        n7 = (Button)findViewById(R.id.n7);
        n8 = (Button)findViewById(R.id.n8);
        n9 = (Button)findViewById(R.id.n9);
        n0 = (Button)findViewById(R.id.n0);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        multiply = (Button)findViewById(R.id.multiply);
        clear = (Button)findViewById(R.id.clear);
        division = (Button)findViewById(R.id.division);
        result = (Button)findViewById(R.id.result);
        text = (EditText) findViewById(R.id.text);
    }

    public void number(View view, String s){
        tmp1 += s;
        text.setText(tmp1);
        getText = text.getText().toString();
    }

    public void sign(View view, String s){
        getText = text.getText().toString();
        arr.add(getText);
        arr.add(s);
        tmp1 = "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.n1:
                number(view, "1");
                break;
            case R.id.n2:
                number(view, "2");
                break;
            case R.id.n3:
                number(view, "3");
                break;
            case R.id.n4:
                number(view, "4");
                break;
            case R.id.n5:
                number(view, "5");
                break;
            case R.id.n6:
                number(view, "6");
                break;
            case R.id.n7:
                number(view, "7");
                break;
            case R.id.n8:
                number(view, "8");
                break;
            case R.id.n9:
                number(view, "9");
                break;
            case R.id.n0:
                number(view, "0");
                break;
            case R.id.plus:
                sign(view, "+");
                break;
            case R.id.minus:
                sign(view, "-");
                break;
            case R.id.division:
                sign(view, "/");
                break;
            case R.id.multiply:
                sign(view, "*");
                break;
            case R.id.result:
                arr.add(getText); //마지막 입력을 배열에 추가
                itmp1 = Integer.parseInt(arr.get(0));
                for(String i : arr){
                    //System.out.println(i);
                    if(i=="+"){tmp2 = "+";}
                    else if(i=="-"){tmp2 = "-";}
                    else if(i=="/"){tmp2 = "/";}
                    else if(i=="*"){tmp2 = "*";}
                    else{
                        if(tmp2=="+"){ itmp1 = itmp1 + Integer.parseInt(i);} //정수로 변환 후 계산
                        else if(tmp2=="-"){ itmp1 = itmp1 - Integer.parseInt(i);}
                        else if(tmp2=="/"){ itmp1 = itmp1 / Integer.parseInt(i);}
                        else if(tmp2=="*"){ itmp1 = itmp1 * Integer.parseInt(i);}
                    }
                }
                text.setText(Integer.toString(itmp1));
                break;
            case R.id.clear:
                tmp1 = "";
                tmp2 = "";
                arr.clear();
                text.setText(""); //모두 초기화
                break;
        }
    }
}