package cfb.com.chartlibrary.compute;

import android.graphics.Paint;

import java.text.NumberFormat;
import java.util.ArrayList;

import cfb.com.chartlibrary.interfaces.iData.IDotFigureData;
import cfb.com.chartlibrary.interfaces.iData.IYAxisData;

/**
 * 描述 ： Y轴计算类
 */
public class ComputeYAxis<T extends IDotFigureData> extends Compute {

    protected IYAxisData yAxisData;
    private NumberFormat numberFormat;
    private Paint paint = new Paint();
    /**
     * 限制收敛次数
     */
    private int times = 0;

    public ComputeYAxis(IYAxisData axisData) {
        super(axisData);
        yAxisData = axisData;
        paint.setColor(yAxisData.getColor());
        paint.setTextSize(yAxisData.getTextSize());
        paint.setStrokeWidth(yAxisData.getPaintWidth());
        //设置小数点位数
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(yAxisData.getDecimalPlaces());
    }

    /**
     * 计算坐标系
     *
     * @param mBarLineCurveData 坐标集合
     */
    public void computeYAxis(ArrayList<T> mBarLineCurveData) {
        for (int i = 0; i < mBarLineCurveData.size(); i++) {
            IDotFigureData barLineCurveData = mBarLineCurveData.get(i);
            float maxY = barLineCurveData.getValue().get(0).y;
            float minY = barLineCurveData.getValue().get(0).y;
            initAxis(barLineCurveData, maxY, minY, i);
        }
        //默认所有的BarLineCurveData。getValue()长度相同
        if (mBarLineCurveData.size() > 0)
            initScaling(yAxisData.getMinimum(), yAxisData.getMaximum(), mBarLineCurveData.get(0).getValue().size(), yAxisData);
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
        //默认所有的BarLineCurveData。getValue()长度相同
        if (mBarLineCurveData.size() > 0)
            initScaling(yAxisData.getMinimum(), yAxisData.getMaximum(), mBarLineCurveData.get(0).getValue().size(), yAxisData);
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
            yAxisData.setNarrowMin(min);
            yAxisData.setNarrowMax(max);
        } else {
            yAxisData.setNarrowMin(min < yAxisData.getNarrowMin() ? min : yAxisData.getNarrowMin());
            yAxisData.setNarrowMax(max > yAxisData.getNarrowMax() ? max : yAxisData.getNarrowMax());
        }
        initMaxMin(max, min, count, yAxisData);
    }

    /**
     * Y轴收敛方法
     *
     * @param barLineCurveData 图表数据
     */
    public void convergence(ArrayList<T> barLineCurveData) {
        times++;
        int count = 0;
        int newCount = 0;
        //二次处理字符过长
        while ((yAxisData.getInterval() * count + yAxisData.getMinimum()) <= yAxisData.getMaximum()) {
            count++;
        }
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float stringLength = descent - ascent;
        while (count * stringLength > yAxisData.getAxisLength()) {
            count = count / 2;
            newCount++;
        }
        yAxisData.setInterval(newCount != 0 ? yAxisData.getInterval() * newCount * 2 : yAxisData.getInterval());
        //收敛
        while (yAxisData.getNarrowMin() - yAxisData.getMinimum() > yAxisData.getInterval()) {
            yAxisData.setMinimum(yAxisData.getMinimum() + yAxisData.getInterval());
        }

        while (yAxisData.getMaximum() - yAxisData.getNarrowMax() > yAxisData.getInterval()) {
            yAxisData.setMaximum(yAxisData.getMaximum() - yAxisData.getInterval());
        }

        if (yAxisData.getMaximum() - yAxisData.getMinimum() <= (yAxisData.getInterval() * 2) && times < 5) {
            initScaling(yAxisData.getMinimum(), yAxisData.getMaximum(), barLineCurveData.get(0).getValue().size(), yAxisData);
            convergence(barLineCurveData);
        }
    }
}
