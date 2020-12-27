package com.hufs.hw3_3_save_restoretestactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1, checkBox2;
    Button btn1, btn2;
    TextView text;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.count);
        checkBox1 = (CheckBox) findViewById(R.id.food1);
        checkBox2 = (CheckBox) findViewById(R.id.food2);
        btn1 = (Button)findViewById(R.id.up);
        btn2 = (Button)findViewById(R.id.down);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(checkBox1.isChecked() && checkBox2.isChecked()){
                    count += 2;
                    text.setText("현재 개수=" + count);
                }else if(checkBox1.isChecked() || checkBox2.isChecked()){
                    count++;
                    text.setText("현재 개수=" + count);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(checkBox1.isChecked() && checkBox2.isChecked()){
                    count -= 2;
                    text.setText("현재 개수=" + count);
                }else if(checkBox1.isChecked() || checkBox2.isChecked()){
                    count--;
                    text.setText("현재 개수=" + count);
                }
            }
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            text.setText("현재 개수=" + count);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }
}