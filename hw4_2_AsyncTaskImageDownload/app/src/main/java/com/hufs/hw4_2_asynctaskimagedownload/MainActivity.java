package com.hufs.hw4_2_asynctaskimagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    EditText etUrl;
    ImageView iView;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownload = (Button) findViewById(R.id.btn_download);
        etUrl = (EditText) findViewById(R.id.et_url);
        iView = (ImageView) findViewById(R.id.iv_image);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CounterTask().execute(etUrl);
            }
        });
    }

    private class CounterTask extends AsyncTask<EditText, String, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(EditText... strings) {
            try {
                URL url = new URL(strings[0].getText().toString());
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();
                System.out.println("URL : " + url);
                InputStream iStream = urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(iStream);
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Image downloaded error", Toast.LENGTH_SHORT).show();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image){
            iView.setImageBitmap(image);
        }
    }
}

