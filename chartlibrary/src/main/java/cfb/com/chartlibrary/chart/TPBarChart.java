package cfb.com.chartlibrary.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import cfb.com.chartlibrary.interfaces.iChart.IBarChart;
import cfb.com.chartlibrary.interfaces.iData.IBarData;
import cfb.com.chartlibrary.render.TPBarChartRender;
import cfb.com.chartlibrary.render.TPOffsetXAxisRender;
import cfb.com.chartlibrary.render.TPYAxisRender;

/**
 * 可视化图表控件 -> 柱状图类型
 * Created by fengbincao on 2017/7/9.
 */

public class TPBarChart extends TPDotFigureChart<IBarData> implements IBarChart {

    // 单个柱状图的宽度，默认为40
    private float barWidth = 50;

    public TPBarChart(Context context) {
        super(context);
    }

    public TPBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TPBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWeight, int oldHeight) {
        super.onSizeChanged(w, h, oldWeight, oldHeight);
        chartRenderList.clear();
        float offset;
        //初始化基类中的chartRenderList
        for (int i = 0; i < mDataList.size(); i++) {
            offset = mXAxisData.getInterval() * mXAxisData.getAxisScale() / 2 - barWidth * mDataList.size() / 2 + barWidth * i;
            TPBarChartRender mBarChartRender = new TPBarChartRender(mDataList.get(i), mXAxisData, mYAxisData, offset, barWidth);
            chartRenderList.add(mBarChartRender);
        }
    }

    @Override
    protected void axisScale() {
        mXAxisData.setAxisScale(mXAxisData.getAxisLength() / (mXAxisData.getMaximum() - mXAxisData.getMinimum() + mXAxisData.getInterval()));
        mYAxisData.setAxisScale(mYAxisData.getAxisLength() / (mYAxisData.getMaximum() - mYAxisData.getMinimum()));
        mXAxisOffsetRender = new TPOffsetXAxisRender(mXAxisData);
        mYAxisRender = new TPYAxisRender(mYAxisData, mXAxisData);
    }

    @Override
    protected void axisRender(Canvas canvas) {
        // 绘制坐标轴内容
        mXAxisOffsetRender.drawGraph(canvas);
        //mYAxisRender.drawGraph(canvas);
    }

    @Override
    public void setBarWidth(float barWidth) {
        this.barWidth = barWidth;
    }

    @Override
    public void computeYAxis() {
        mComputeYAxis.computeYAxisMin(mDataList);
    }

}
