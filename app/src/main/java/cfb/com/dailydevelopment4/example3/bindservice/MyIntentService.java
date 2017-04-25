package cfb.com.dailydevelopment4.example3.bindservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 实现IntentService
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        // 调用父类的有参构造函数
        super("MyIntentService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // 打印执行的线程id
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread(). getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }
}
