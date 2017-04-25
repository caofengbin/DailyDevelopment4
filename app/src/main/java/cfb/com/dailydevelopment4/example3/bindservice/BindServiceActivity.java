package cfb.com.dailydevelopment4.example3.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cfb.com.dailydevelopment4.R;

/**
 * Activity与Service的交互测试
 */
public class BindServiceActivity extends AppCompatActivity {

    // 引用Service中定义的Binder
    private MyService2.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService2.DownloadBinder) service;
            // 在这里可以调用Binder中的方法
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        ButterKnife.bind(this);
    }

    @BindView(R.id.bind_service)
    public Button mBindServiceBtn;

    @BindView(R.id.unbind_service)
    public Button mUnBindServiceBtn;

    @BindView(R.id.fore_ground_service)
    public Button mForeGroundServiceBtn;

    @BindView(R.id.start_intent_service)
    public Button mIntentServiceBtn;

    @OnClick(R.id.bind_service)
    public void bindService() {
        Intent bindIntent = new Intent(this, MyService2.class);
        bindService(bindIntent, connection, BIND_AUTO_CREATE);              // 绑定Service的核心
    }

    @OnClick(R.id.unbind_service)
    public void unBindService() {
        unbindService(connection);
    }

    @OnClick(R.id.fore_ground_service)
    public void startForeGroundService() {
        Intent bindIntent = new Intent(this, ForGroundService.class);
        startService(bindIntent);
    }

    @OnClick(R.id.start_intent_service)
    public void startIntentService() {
        Intent bindIntent = new Intent(this, MyIntentService.class);
        Log.d("MainActivity", "Thread id is " + Thread.currentThread().getId());
        startService(bindIntent);
    }
}
