package cfb.com.chartlibrary.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.text.NumberFormat;
import java.util.ArrayList;

import cfb.com.chartlibrary.animation.ChartAnimator;
import cfb.com.chartlibrary.compute.ComputeXAxis;
import cfb.com.chartlibrary.compute.ComputeYAxis;
import cfb.com.chartlibrary.data.TPXAxisData;
import cfb.com.chartlibrary.data.TPYAxisData;
import cfb.com.chartlibrary.interfaces.iChart.IDotFigureChart;
import cfb.com.chartlibrary.interfaces.iData.IDotFigureData;
import cfb.com.chartlibrary.interfaces.iData.IXAxisData;
import cfb.com.chartlibrary.interfaces.iData.IYAxisData;
import cfb.com.chartlibrary.render.XAxisOffsetTPRender;
import cfb.com.chartlibrary.render.XAxisTPRender;
import cfb.com.chartlibrary.render.YAxisTPRender;

/**
 * 可视化图表控件 -> 点状图类型的基类
 * 可能包括：柱状图、折线图、曲线图绘制基类
 * Created by fengbincao on 2017/7/9.
 */

public abstract class TPDotFigureChart<T extends IDotFigureData> extends TPChart<T> implements IDotFigureChart {

    // X,Y坐标轴部分相关数据信息
    protected IXAxisData mXAxisData = new TPXAxisData();
    protected IYAxisData mYAxisData = new TPYAxisData();












    
    protected ComputeXAxis mComputeXAxis = new ComputeXAxis(mXAxisData);
    protected ComputeYAxis mComputeYAxis = new ComputeYAxis(mYAxisData);

    public boolean convergenceFlag = true;

    // 柱状图，组合图使用
    protected XAxisOffsetTPRender mXAxisOffsetRender;

    // 曲线图，折线图使用
    protected XAxisTPRender mXAxisRender;

    // 均会用到
    protected YAxisTPRender mYAxisRender;

    protected float animatedValue;
    public boolean isAnimated = true;
    protected ChartAnimator mChartAnimator;

    public TPDotFigureChart(Context context) {
        super(context);
    }

    public TPDotFigureChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TPDotFigureChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWeight, int oldHeight) {
        super.onSizeChanged(w, h, oldWeight, oldHeight);

        // 坐标轴长度为整体View的 80%
        mXAxisData.setAxisLength(mWidth * 0.8f);
        mYAxisData.setAxisLength(mHeight * 0.8f);

        // 启动动画效果
        animated();

        if (convergenceFlag) {
            // 循环收敛
            mComputeXAxis.convergence(mDataList);
            mComputeYAxis.convergence(mDataList);
        }

        axisScale();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mViewWidth / 2 - mXAxisData.getAxisLength() / 2, mViewHeight / 2 + mYAxisData.getAxisLength() / 2);
        canvas.save();
        canvas.scale(1, -1);
        axisRender(canvas);
        canvas.restore();
        drawGraphical(canvas);
    }

    /**
     * 动画方法，默认动画时间两秒，生成监听值animatedValue
     * 如果不使用动画，则直接设置监听值为1
     */
    protected void animated() {
        if (!isAnimated) {
            animatedValue = 1;
        } else {
            mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animatedValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            };
            mChartAnimator = new ChartAnimator(mAnimatorUpdateListener);
            mChartAnimator.animatedY(1000, 1);
        }
    }

    /**
     * 图表渲染方法
     *
     * @param canvas 渲染绘制的画布
     */
    protected void drawGraphical(Canvas canvas) {
        for (int i = 0; i < chartRenderList.size(); i++) {
            chartRenderList.get(i).drawGraph(canvas, animatedValue);
        }
    }

    /**
     * 计算坐标轴刻度长度与View长度的比
     */
    protected abstract void axisScale();

    /**
     * 渲染X、Y轴
     *
     * @param canvas 画布
     */
    protected abstract void axisRender(Canvas canvas);

    @Override
    public void computeXAxis() {
        mComputeXAxis.computeXAxis(mDataList);
    }

    @Override
    public void computeYAxis() {
        mComputeYAxis.computeYAxis(mDataList);
    }

    @Override
    public void setData(T chartData) {
        mDataList.clear();
        mDataList.add(chartData);
        computeXAxis();
        computeYAxis();
    }

    @Override
    public void setDataList(ArrayList<T> chartDataList) {
        mDataList = chartDataList;
        computeXAxis();
        computeYAxis();
    }

    @Override
    public int getCurrentWidth() {
        convergenceFlag = false;
        int wrapSize;
        if (mDataList != null && mDataList.size() > 0) {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMinimumFractionDigits(mXAxisData.getDecimalPlaces());
            paintText.setTextSize(mXAxisData.getTextSize());
            paintText.setStrokeWidth(mXAxisData.getPaintWidth());
            float xAxisWidth = paintText.measureText(numberFormat.format(mXAxisData.getMaximum()))
                    * (float) Math.ceil((mXAxisData.getMaximum() - mXAxisData.getMinimum()) / mXAxisData.getInterval());
            wrapSize = (int) (xAxisWidth * 1.2f);
        } else {
            wrapSize = 0;
        }
        return wrapSize;
    }

    @Override
    public int getCurrentHeight() {

        convergenceFlag = false;
        Paint paintValue = new Paint();
        int wrapSize;
        if (mDataList != null && mDataList.size() > 0) {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMinimumFractionDigits(mYAxisData.getDecimalPlaces());
            paintText.setStrokeWidth(mYAxisData.getPaintWidth());
            paintText.setTextSize(mYAxisData.getTextSize());
            Paint.FontMetrics fontMetrics = paintText.getFontMetrics();
            float top = fontMetrics.top;
            float bottom = fontMetrics.bottom;
            float yAxisWidth = (bottom - top) * (float) Math.ceil((mYAxisData.getMaximum() - mYAxisData.getMinimum())
                    / mYAxisData.getInterval());
            paintValue.setStrokeWidth(mDataList.get(0).getPaintWidth());
            paintValue.setTextSize(mDataList.get(0).getTextSize());
            Paint.FontMetrics fontMetricsValue = paintValue.getFontMetrics();
            float valueWidth = fontMetricsValue.bottom - fontMetricsValue.top;
            wrapSize = (int) (yAxisWidth * 2f + valueWidth * 2);
        } else {
            wrapSize = 0;
        }
        return wrapSize;
    }

    @Override
    public void setAxisTextSize(float axisTextSize) {
        mXAxisData.setTextSize(axisTextSize);
        mYAxisData.setTextSize(axisTextSize);
    }

    @Override
    public void setAxisColor(int axisColor) {
        mXAxisData.setColor(axisColor);
        mYAxisData.setColor(axisColor);
    }

    @Override
    public void setAxisWidth(float axisWidth) {
        mXAxisData.setPaintWidth(axisWidth);
        mYAxisData.setPaintWidth(axisWidth);
    }

    @Override
    public void setXAxisUnit(String XAxisUnit) {
        mXAxisData.setUnit(XAxisUnit);
    }

    @Override
    public void setYAxisUnit(String YAxisUnit) {
        mYAxisData.setUnit(YAxisUnit);
    }


}
