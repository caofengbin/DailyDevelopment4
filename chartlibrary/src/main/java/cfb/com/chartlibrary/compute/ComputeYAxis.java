package cfb.com.chartlibrary.compute;

import android.graphics.Paint;

import java.text.NumberFormat;
import java.util.ArrayList;

import cfb.com.chartlibrary.interfaces.iData.IDotFigureData;
import cfb.com.chartlibrary.interfaces.iData.IYAxisData;

/**
 * 可视化图表控件 -> Y 轴计算工具类
 * Created by fengbincao on 2017/7/10.
 */
public class ComputeYAxis<T extends IDotFigureData> extends Compute {

    private IYAxisData mAxisData;
    private NumberFormat numberFormat;
    private Paint paint = new Paint();

    // 限制收敛次数
    private int scaleTimes = 0;

    public ComputeYAxis(IYAxisData axisData) {
        super(axisData);
        mAxisData = axisData;

        // 初始化坐标轴相关的设置信息
        paint.setColor(mAxisData.getColor());
        paint.setTextSize(mAxisData.getTextSize());
        paint.setStrokeWidth(mAxisData.getPaintWidth());
        //设置小数点位数
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(mAxisData.getDecimalPlaces());
    }

    /**
     * 计算坐标系
     *
     * @param dotFigureDataArray 坐标集合
     */
    public void computeYAxis(ArrayList<T> dotFigureDataArray) {
        for (int i = 0; i < dotFigureDataArray.size(); i++) {
            IDotFigureData dotFigureData = dotFigureDataArray.get(i);
            // 通过 barLineCurveData.getValue() 取到的是点集的数据
            float maxY = dotFigureData.getValue().get(0).y;
            float minY = dotFigureData.getValue().get(0).y;
            initAxis(dotFigureData, maxY, minY, i);
        }

        // 计算Y轴的区间大小
        if (dotFigureDataArray.size() > 0) {
            initScaling(mAxisData.getMinimum(), mAxisData.getMaximum(),
                    dotFigureDataArray.get(0).getValue().size(), mAxisData);
        }
    }

    /**
     * 计算Y轴最大值、最小值,强制设置最小值为0
     *
     * @param mBarLineCurveData 图表数据
     */
    public void computeYAxisMin(ArrayList<T> mBarLineCurveData) {
        for (int i = 0; i < mBarLineCurveData.size(); i++) {
            IDotFigureData barLineCurveData = mBarLineCurveData.get(i);
            float maxY = barLineCurveData.getValue().get(0).y;
            initAxis(barLineCurveData, maxY, 0, i);
        }
        // 默认所有的BarLineCurveData。getValue()长度相同
        if (mBarLineCurveData.size() > 0)
            initScaling(mAxisData.getMinimum(), mAxisData.getMaximum(), mBarLineCurveData.get(0).getValue().size(), mAxisData);
    }

    /**
     * * 计算Y轴数据
     *
     * @param mBarLineCurveData 单条曲线点数据
     * @param max               最大值
     * @param min               最小值
     * @param count             第几组数据
     */
    private void initAxis(IDotFigureData mBarLineCurveData, float max, float min, int count) {
        // 遍历数组，取出最大值和最小值
        for (int i = 1; i < mBarLineCurveData.getValue().size(); i++) {
            max = Math.max(mBarLineCurveData.getValue().get(i).y, max);
            min = Math.min(mBarLineCurveData.getValue().get(i).y, min);
        }

        if (max < 0) {
            max = -max;
            maxAxisSign = -1;
        }
        if (min < 0) {
            min = -min;
            minAxisSign = -1;
        }
        if (max == min) {
            min = 0;
        }
        min = min * minAxisSign;
        max = max * maxAxisSign;
        if (max < min) {
            float center = min;
            min = max;
            max = center;
        }
        if (count == 0) {
            mAxisData.setNarrowMin(min);
            mAxisData.setNarrowMax(max);
        } else {
            mAxisData.setNarrowMin(min < mAxisData.getNarrowMin() ? min : mAxisData.getNarrowMin());
            mAxisData.setNarrowMax(max > mAxisData.getNarrowMax() ? max : mAxisData.getNarrowMax());
        }
        initMaxMin(max, min, count, mAxisData);
    }

    /**
     * Y轴收敛方法
     *
     * @param barLineCurveData 图表数据
     */
    public void convergence(ArrayList<T> barLineCurveData) {
        scaleTimes++;
        int count = 0;
        int newCount = 0;
        //二次处理字符过长
        while ((mAxisData.getInterval() * count + mAxisData.getMinimum()) <= mAxisData.getMaximum()) {
            count++;
        }
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float stringLength = descent - ascent;
        while (count * stringLength > mAxisData.getAxisLength()) {
            count = count / 2;
            newCount++;
        }
        mAxisData.setInterval(newCount != 0 ? mAxisData.getInterval() * newCount * 2 : mAxisData.getInterval());
        //收敛
        while (mAxisData.getNarrowMin() - mAxisData.getMinimum() > mAxisData.getInterval()) {
            mAxisData.setMinimum(mAxisData.getMinimum() + mAxisData.getInterval());
        }

        while (mAxisData.getMaximum() - mAxisData.getNarrowMax() > mAxisData.getInterval()) {
            mAxisData.setMaximum(mAxisData.getMaximum() - mAxisData.getInterval());
        }

        if (mAxisData.getMaximum() - mAxisData.getMinimum() <= (mAxisData.getInterval() * 2) && scaleTimes < 5) {
            initScaling(mAxisData.getMinimum(), mAxisData.getMaximum(), barLineCurveData.get(0).getValue().size(), mAxisData);
            convergence(barLineCurveData);
        }
    }
}
