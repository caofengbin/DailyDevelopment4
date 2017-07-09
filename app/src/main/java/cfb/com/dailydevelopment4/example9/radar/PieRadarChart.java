package cfb.com.dailydevelopment4.example9.radar;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by fengbincao on 2017/7/5.
 */

public abstract class PieRadarChart<T extends IChartData> extends Chart<T> implements IPieRadarChart {

//    protected ArrayList<TPChartRender> chartRenderList = new ArrayList<>();

    public PieRadarChart(Context context) {
        super(context);
    }

    public PieRadarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieRadarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setData(T chartData) {
        mDataList.clear();
        mDataList.add(chartData);
    }

    @Override
    public void setDataList(ArrayList<T> chartDataList) {
        mDataList = chartDataList;
    }
}
