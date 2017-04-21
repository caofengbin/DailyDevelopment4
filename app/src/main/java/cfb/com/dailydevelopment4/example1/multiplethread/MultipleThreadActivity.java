package cfb.com.dailydevelopment4.example1.multiplethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cfb.com.dailydevelopment4.R;

public class MultipleThreadActivity extends AppCompatActivity implements View.OnClickListener {

	public static final int UPDATE_TEXT = 1;

	private static final String TAG = "fengbincao";

	private TextView text;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case UPDATE_TEXT :
					// 在这里执行UI操作
					text.setText("新的文字内容");
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_thread);

		text = (TextView) findViewById(R.id.text);
		Button changeText = (Button) findViewById(R.id.change_text);
		changeText.setOnClickListener(this);
		boolean isUIThread = Looper.myLooper() == Looper.getMainLooper();
		if(isUIThread) {
			Log.e(TAG,"代码1在UI线程执行");
		} else {
			Log.e(TAG,"代码1在子线程执行");
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.change_text:
				new Thread(new Runnable() {
					@Override
					public void run() {
						boolean isUIThread = Looper.myLooper() == Looper.getMainLooper();
						if(isUIThread) {
							Log.e(TAG,"代码2在UI线程执行");
						} else {
							Log.e(TAG,"代码2在子线程执行");
						}
						Message message = new Message();
						message.what = UPDATE_TEXT;
						mHandler.sendMessage(message);
					}
				}).start();
				break;
			default:
				break;
		}
	}
}
