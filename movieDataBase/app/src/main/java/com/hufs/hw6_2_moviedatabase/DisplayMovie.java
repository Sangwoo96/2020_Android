package com.hufs.hw6_2_moviedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMovie extends AppCompatActivity {
    DBHelper mydb;
    private EditText movieName;
    private EditText pYear;
    private EditText director;
    private EditText rating;
    private EditText nation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymovie);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);

        Button saveBtn = findViewById(R.id.saveBtn);
        Button updateBtn = findViewById(R.id.updateBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        movieName = findViewById(R.id.movieName);
        pYear = findViewById(R.id.pYear);
        director = findViewById(R.id.director);
        rating = findViewById(R.id.rating);
        nation = findViewById(R.id.nation);

        mydb = new DBHelper(this);
        Cursor res = mydb.getData(id);
        res.moveToFirst();

        if(res.getCount() != 0){
            movieName.setText(res.getString(res.getColumnIndex("name")));
            pYear.setText(res.getString(res.getColumnIndex("year")));
            director.setText(res.getString(res.getColumnIndex("director")));
            rating.setText(res.getString(res.getColumnIndex("rating")));
            nation.setText(res.getString(res.getColumnIndex("nation")));
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = movieName.getText().toString();
                String year = pYear.getText().toString();
                String director = DisplayMovie.this.director.getText().toString();
                String rating = DisplayMovie.this.rating.getText().toString();
                String nation = DisplayMovie.this.nation.getText().toString();
                mydb.insertMovie(name, director, year, nation, rating);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = movieName.getText().toString();
                String year = pYear.getText().toString();
                String director = DisplayMovie.this.director.getText().toString();
                String rating = DisplayMovie.this.rating.getText().toString();
                String nation = DisplayMovie.this.nation.getText().toString();
                mydb.updateMovie(id ,name, director, year, nation, rating);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.deleteMovie(id);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
