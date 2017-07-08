package cfb.com.chartlibrary.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import cfb.com.chartlibrary.interfaces.iChart.IChart;
import cfb.com.chartlibrary.interfaces.iData.IChartData;
import cfb.com.chartlibrary.render.ChartRender;

/**
 * 所有图表绘制基类
 * Created by fengbincao on 2017/7/8.
 */

public abstract class Chart<T extends IChartData> extends View implements IChart {

    // 原始的宽高
    protected int mViewWidth, mViewHeight;

    // 去除padding部分的宽高
    protected int mWidth, mHeight;

    protected ArrayList<T> mDataList = new ArrayList<>();

    protected ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;

    protected ArrayList<ChartRender> chartRenderList = new ArrayList<>();

    /**
     * 测量用画笔
     */
    protected Paint paintText = new Paint();

    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                Math.max(getSuggestedMinimumWidth(),
                        resolveSize(getCurrentWidth(),
                                widthMeasureSpec)),
                Math.max(getSuggestedMinimumHeight(),
                        resolveSize(getCurrentHeight(),
                                heightMeasureSpec)));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);

        mViewWidth = w;
        mViewHeight = h;

        mWidth = mViewWidth - getPaddingLeft() - getPaddingRight();
        mHeight = mViewHeight - getPaddingTop() - getPaddingBottom();
    }

    /**
     * 设置图表数据
     * @param chartData 图表数据
     */
    public abstract void setData(T chartData);

    /**
     * 设置图标数据
     * @param chartDataList 图表数据
     */
    public abstract void setDataList(ArrayList<T> chartDataList);

    public abstract int getCurrentWidth();

    public abstract int getCurrentHeight();
}
