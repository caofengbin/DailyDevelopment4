package cfb.com.dailydevelopment4.example2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service的基本使用
 */
public class MyService extends Service {

	private static final String TAG = "MyService";

	public MyService() {
		Log.d(TAG,"构造方法执行");
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

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	private DownloadBinder mBinder = new DownloadBinder();

	class DownloadBinder extends Binder {

		public void startDownload() {
			Log.d(TAG, "startDownload executed");
		}

		public void getProgress() {
			Log.d(TAG, "getProgress executed");
		}
	}
}
