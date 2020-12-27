package com.hufs.hw5_2_randomnumberservice;

        import android.app.Service;
        import android.content.Intent;
        import android.os.Binder;
        import android.os.IBinder;

        import java.util.Random;

public class MyService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    // 클라이언트를 위한 메소드
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
