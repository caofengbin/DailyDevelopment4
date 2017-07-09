package cfb.com.dailydevelopment4.example10.barchart;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.ArrayList;

import cfb.com.chartlibrary.chart.BarTPDotFigureChart;
import cfb.com.chartlibrary.data.BarDataTPDotFigureData;
import cfb.com.chartlibrary.interfaces.iData.IBarDataI;
import cfb.com.dailydevelopment4.R;

public class BarChartActivity extends AppCompatActivity {

    private ArrayList<IBarDataI> mDataList = new ArrayList<>();
    private BarDataTPDotFigureData mBarData = new BarDataTPDotFigureData();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chasrt);
        BarTPDotFigureChart barChart = (BarTPDotFigureChart)findViewById(R.id.barChart);
        initData();
        barChart.setDataList(mDataList);
        barChart.setXAxisUnit("X单位");
        barChart.setYAxisUnit("Y单位");
    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            mPointArrayList.add(new PointF(points[i][0], points[i][1]));
        }
        mBarData.setValue(mPointArrayList);
        mBarData.setColor(Color.RED);
        mBarData.setPaintWidth(pxToDp(5));
        mBarData.setTextSize(pxToDp(10));

        mDataList.add(mBarData);
    }

    protected float[][] points = new float[][]{{1,10}, {2,47}, {3,11}, {4,38}, {5,9},{6,52}, {7,14}, {8,37}, {9,29}, {10,31}};
    protected float[][] points2 = new float[][]{{1,52}, {2,13}, {3,51}, {4,20}, {5,19},{6,20}, {7,54}, {8,7}, {9,19}, {10,41}};
    protected int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    protected float pxToDp(float value){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float valueDP= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,metrics);
        return valueDP;
    }
}
