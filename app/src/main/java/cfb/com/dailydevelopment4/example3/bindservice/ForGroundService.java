package cfb.com.dailydevelopment4.example3.bindservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import cfb.com.dailydevelopment4.R;

/**
 * 前台Service
 */
public class ForGroundService extends Service {

    private static final String TAG = "ForGroundService";

    public ForGroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onBind executed");
        Intent intent = new Intent(this, BindServiceActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();

        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
