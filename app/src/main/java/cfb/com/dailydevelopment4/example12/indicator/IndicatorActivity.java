package cfb.com.dailydevelopment4.example12.indicator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cfb.com.dailydevelopment4.MainActivity;
import cfb.com.dailydevelopment4.R;
import cfb.com.dailydevelopment4.example1.multiplethread.MultipleThreadActivity;
import cfb.com.dailydevelopment4.example10.barchart.BarChartActivity;
import cfb.com.dailydevelopment4.example11.draw.TestDrawActivity;
import cfb.com.dailydevelopment4.example2.service.ServiceActivity;
import cfb.com.dailydevelopment4.example3.bindservice.BindServiceActivity;
import cfb.com.dailydevelopment4.example4.okhttp.UseOkHttpActivity;
import cfb.com.dailydevelopment4.example5.download.TestDownloadActivity;
import cfb.com.dailydevelopment4.example6.drawtext.DrawTextActivity;
import cfb.com.dailydevelopment4.example7.animation.AnimationActivity;
import cfb.com.dailydevelopment4.example8.animation2.AnimationActivity2;
import cfb.com.dailydevelopment4.example9.radar.RadarGraphActivity;

public class IndicatorActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    private String[] mainItems;
    private ListView mMainListView;
    private ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        mainItems = getResources().getStringArray(R.array.indicator_string_array);
        mMainListView = (ListView) findViewById(R.id.main_activity_listView2);
        itemAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mainItems);

        mMainListView.setAdapter(itemAdapter);
        mMainListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                startIntent(SampleCirclesInitialPage.class);
                break;
            case 1:
                startIntent(SampleLinesDefault.class);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
            case 11:

                break;
        }
    }

    private void startIntent(Class class1){
        Intent intent = new Intent(IndicatorActivity.this,class1);
        startActivity(intent);
    }
}

