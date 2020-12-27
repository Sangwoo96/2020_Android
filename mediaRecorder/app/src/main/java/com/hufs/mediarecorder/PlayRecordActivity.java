package com.hufs.mediarecorder;

import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlayRecordActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    String filename;
    String path;

    Button mBtPlay = null;

    MediaPlayer mPlayer = null;

    boolean isPlaying = false;

    SurfaceView mSurface = null;
    SurfaceHolder mSurfaceHolder = null;

    Camera mCamera = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playrecorder);

        Intent intent = getIntent();
        filename = intent.getExtras().getString("filename");

        path = getExternalCacheDir().getAbsolutePath() + "/" + filename;

        mSurface = (SurfaceView)findViewById(R.id.sv);
        mBtPlay = (Button) findViewById(R.id.bt_play);

        mPlayer = new MediaPlayer();

        initVideoRecorder();

        mBtPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying == false) {
                    try {
                        System.out.println("==================" + path);
                        mPlayer.setDataSource(path);
                        mPlayer.setDisplay(mSurfaceHolder);
                        mPlayer.prepare();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPlayer.start();
                    isPlaying = true;
                    mBtPlay.setText("Stop Playing");
                } else {
                    mPlayer.stop();
                    isPlaying = false;
                    mBtPlay.setText("Start Playing");
                    mCamera = null;
                }
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPlaying = false;
                mBtPlay.setText("Start Playing");
            }
        });
    }

    void initVideoRecorder() {
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        mSurfaceHolder = mSurface.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
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
