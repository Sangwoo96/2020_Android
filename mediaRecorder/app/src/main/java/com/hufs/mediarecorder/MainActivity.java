package com.hufs.mediarecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int number = 0;

    Button mBtRecord = null;
    Button mBtList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtRecord =  (Button)findViewById(R.id.recordBtn);
        mBtList =  (Button)findViewById(R.id.listBtn);

    }

    public void startRecord(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("number", number);
        Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
        intent.putExtras(bundle);
        number++;
        startActivity(intent);
    }

    public void showList(View view) {
        Intent intent = new Intent(getApplicationContext(), RecordListActivity.class);
        startActivity(intent);
    }
}
