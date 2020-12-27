package com.hufs.hw1_2_singletouchview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singleTouchView view = new singleTouchView(this);
        setContentView(view);
    }
}