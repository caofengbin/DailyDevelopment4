package cfb.com.dailydevelopment4.example9.radar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.ArrayList;

import cfb.com.dailydevelopment4.R;

public class RadarGraphActivity extends AppCompatActivity {

    private RadarData mRadarData = new RadarData();
    private RadarData mRadarData2 = new RadarData();
    private ArrayList<IRadarData> radarDataList = new ArrayList<>();
    private ArrayList<Float> radarValue = new ArrayList<>();
    private ArrayList<Float> radarValue2 = new ArrayList<>();

    protected float[][] points = new float[][]{{1,10}, {2,47}, {3,11}, {4,38}, {5,9},{6,52}, {7,14}, {8,37}, {9,29}, {10,31}};
    protected float[][] points2 = new float[][]{{1,52}, {2,13}, {3,51}, {4,20}, {5,19},{6,20}, {7,54}, {8,7}, {9,19}, {10,41}};

    protected float pxTodp(float value){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float valueDP= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,metrics);
        return valueDP;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_graph);

        initData();
        RadarChart radarChart = (RadarChart) findViewById(R.id.radarChart);
        radarChart.setDataList(radarDataList);
        radarChart.setTypes(new String[]{"营业收入", "毛利率", "净利润", "每股净资产", "每股收益"});

    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            radarValue.add(points[i][1]);
            radarValue2.add(points2[i][1]);
        }
        mRadarData.setValue(radarValue);
        mRadarData.setColor(Color.MAGENTA);
        mRadarData.setPaintWidth(pxTodp(3));
        mRadarData2.setValue(radarValue2);
        mRadarData2.setColor(Color.CYAN);
        mRadarData2.setPaintWidth(pxTodp(3));
        radarDataList.add(mRadarData);
        radarDataList.add(mRadarData2);
    }

}
