package com.hufs.mediarecorder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class RecordActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    int number = 0;

    Button mBtRecord = null;

    boolean isRecording = false;

    MediaRecorder mRecorder = null;
    String mPath = null;
    SurfaceView mSurface = null;
    SurfaceHolder mSurfaceHolder = null;
    Camera mCamera = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        mSurface = (SurfaceView)findViewById(R.id.sv);
        mBtRecord = (Button)findViewById(R.id.bt_record);

        Intent intent = getIntent();
        number = intent.getExtras().getInt("number");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) !=
                PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE }, 10);
        }
        else{
            mRecorder = new MediaRecorder();
            mBtRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initVideoRecorder();
                    startVideoRecorder();
                }
            });
        }
    }

    private void initVideoRecorder(){
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        mSurfaceHolder = mSurface.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    private void startVideoRecorder() {
        if(isRecording) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            mCamera.lock();
            isRecording = false;
            mBtRecord.setText("Start Recording");
            finish();
        }
        else {
            mRecorder = new MediaRecorder();
            mCamera.unlock();
            mRecorder.setCamera(mCamera);
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
            mRecorder.setOrientationHint(90);
            mPath = getExternalCacheDir().getAbsolutePath() + "/record" + number + ".mp4";
            mRecorder.setOutputFile(mPath);
            mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
            try {
                mRecorder.prepare();
                mRecorder.start();
            }catch(Exception e){
                e.printStackTrace();
            }
            isRecording = true;
            mBtRecord.setText("Stop Recording");
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (mCamera == null) {
            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.startPreview();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        mCamera.stopPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
