package cfb.com.chartlibrary.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import cfb.com.chartlibrary.interfaces.iChart.IChart;
import cfb.com.chartlibrary.interfaces.iData.IChartData;
import cfb.com.chartlibrary.render.TPChartRender;

/**
 * 可视化图表控件 -> 图表库中的抽象控件类基类
 * Created by fengbincao on 2017/7/8.
 */

public abstract class TPChart<T extends IChartData> extends View implements IChart {

    // 控件的原始宽高
    protected int mViewWidth;
    protected int mViewHeight;

    // 去除padding部分的宽高
    protected int mWidth;
    protected int mHeight;

    // 动画监听器
    protected ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;

    // 表格中的数据实体部分
    protected ArrayList<T> mDataList = new ArrayList<>();

    // 表格的渲染器数组
    protected ArrayList<TPChartRender> chartRenderList = new ArrayList<>();

    // 测量用画笔
    protected Paint paintText = new Paint();

    public TPChart(Context context) {
        super(context);
    }

    public TPChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TPChart(Context context, AttributeSet attrs, int defStyleAttr) {
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
     * 设置图表数据方法1
     * @param chartData 图表数据
     */
    public abstract void setData(T chartData);

    /**
     * 设置图标数据方法2：传递一个数据数组的形式
     * @param chartDataList 图表数据
     */
    public abstract void setDataList(ArrayList<T> chartDataList);

    /**
     * 子类实现，测量当前控件的宽度
     */
    public abstract int getCurrentWidth();

    /**
     * 子类实现，测量当前控件的高度
     */
    public abstract int getCurrentHeight();
}
