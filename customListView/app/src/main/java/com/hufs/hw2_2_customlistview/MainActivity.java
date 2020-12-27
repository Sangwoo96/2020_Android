package com.hufs.hw2_2_customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list01);

        String[] values = {"Apple", "Apricot", "Avocado", "Banana", "BlackBerry", "BlueBerry", "Cherry", "Coconut"
        , "Cranberry", "Grape Raisin", "Honeydev", "Jackfruit" ,"Lemon", "Lime" ,"Mango" ,"Watermelon"};
        MyAdapter adapter = new MyAdapter(this,values);
        list.setAdapter(adapter);
    }
}