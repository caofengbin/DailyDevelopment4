package cfb.com.dailydevelopment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cfb.com.dailydevelopment4.example1.multiplethread.MultipleThreadActivity;
import cfb.com.dailydevelopment4.example2.service.ServiceActivity;
import cfb.com.dailydevelopment4.example3.bindservice.BindServiceActivity;
import cfb.com.dailydevelopment4.example4.okhttp.UseOkHttpActivity;
import cfb.com.dailydevelopment4.example5.download.TestDownloadActivity;
import cfb.com.dailydevelopment4.example6.drawtext.DrawTextActivity;
import cfb.com.dailydevelopment4.example7.animation.AnimationActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	private String[] mainItems;
	private ListView mMainListView;
	private ArrayAdapter<String> itemAdapter;

//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainItems = getResources().getStringArray(R.array.main_view_string_array);
		mMainListView = (ListView) findViewById(R.id.main_activity_listView);
		itemAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, mainItems);

		mMainListView.setAdapter(itemAdapter);
		mMainListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
		switch (position) {
			case 0:
				startIntent(MultipleThreadActivity.class);
				break;
			case 1:
				startIntent(ServiceActivity.class);
				break;
			case 2:
                startIntent(BindServiceActivity.class);
				break;
			case 3:
                startIntent(UseOkHttpActivity.class);
				break;
			case 4:
                startIntent(TestDownloadActivity.class);
				break;
			case 5:
                startIntent(DrawTextActivity.class);
				break;
			case 6:
                startIntent(AnimationActivity.class);
				break;
			case 7:
				break;
			case 8:
				break;
		}
	}

	private void startIntent(Class class1){
		Intent intent = new Intent(MainActivity.this,class1);
		startActivity(intent);
	}
}
