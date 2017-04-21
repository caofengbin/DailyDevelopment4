package cfb.com.dailydevelopment4.example2.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cfb.com.dailydevelopment4.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

//
		Button startService = (Button) findViewById(R.id.start_service);
		Button stopService = (Button) findViewById(R.id.stop_service);

		startService.setOnClickListener(this);
		stopService.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.start_service:
				// 启动服务
				Intent startIntent = new Intent(this, MyService.class);
				startService(startIntent);
				break;
			case R.id.stop_service:
				// 停止服务
				Intent stopIntent = new Intent(this, MyService.class);
				stopService(stopIntent);
				break;
		}
	}

}
