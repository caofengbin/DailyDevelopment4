package cfb.com.dailydevelopment4.example3.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService2 extends Service {

    private static final String TAG = "MyService2";

    public MyService2() {
    }

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind executed");
        // 返回相应的Binder实例
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy executed");
    }

    /**
     * Activity与Service交互的核心
     */
    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "startDownload executed");
        }

        public int getProgress() {
            Log.d(TAG, "getProgress executed");
            return 0;
        }
    }
}
