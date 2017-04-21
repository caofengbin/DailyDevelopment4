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
;
	}

	@Override
	public void onCreate() {
		super.onCreate();
;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
;
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	private DownloadBinder mBinder = new DownloadBinder();

	class DownloadBinder extends Binder {

		public void startDownload() {
;
		}

		public void getProgress() {
;
		}
	}
}
