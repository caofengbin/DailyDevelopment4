package cfb.com.chartlibrary.compute;

import android.graphics.Paint;

import java.text.NumberFormat;
import java.util.ArrayList;

import cfb.com.chartlibrary.interfaces.iData.IDotFigureData;
import cfb.com.chartlibrary.interfaces.iData.IXAxisData;

/**
 * 可视化图表控件 -> X 轴计算工具类
 * Created by fengbincao on 2017/7/10.
 */
public class ComputeXAxis<T extends IDotFigureData> extends Compute {

    private IXAxisData mAxisData;
    private NumberFormat numberFormat;
    private Paint paint = new Paint();

    // 限制收敛次数
    private int scaleTimes = 0;

    public ComputeXAxis(IXAxisData axisData) {
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
     * 计算坐标系 X 的相关参数
     *
     * @param dotFigureDataArray 坐标集合
     */
    public void computeXAxis(ArrayList<T> dotFigureDataArray) {
        for (int i = 0; i < dotFigureDataArray.size(); i++) {
            IDotFigureData dotFigureData = dotFigureDataArray.get(i);
            // 通过 dotFigureData.getValue() 操作取到的是 点集部分的数据
            int length = dotFigureData.getValue().size();
            float maxX = 0;
            float minX = 0;
            if (length > 0) {
                maxX = Math.max(dotFigureData.getValue().get(length - 1).x, dotFigureData.getValue().get(0).x);
                minX = Math.min(dotFigureData.getValue().get(length - 1).x, dotFigureData.getValue().get(0).x);
            }


            if (maxX < 0) {
                maxX = -maxX;
                maxAxisSign = -1;
            }
            if (minX < 0) {
                minX = -minX;
                minAxisSign = -1;
            }

            if (maxX == minX) {
                minX = 0;
            }
            minX = minX * minAxisSign;
            maxX = maxX * maxAxisSign;
            if (maxX < minX) {
                float center = minX;
                minX = maxX;
                maxX = center;
            }

            if (i == 0) {
                mAxisData.setNarrowMin(minX);
                mAxisData.setNarrowMax(maxX);
            } else {
                mAxisData.setNarrowMin(minX < mAxisData.getNarrowMin() ? minX : mAxisData.getNarrowMin());
                mAxisData.setNarrowMax(maxX > mAxisData.getNarrowMax() ? maxX : mAxisData.getNarrowMax());
            }

            initMaxMin(maxX, minX, i, mAxisData);
        }
        if (dotFigureDataArray.size() > 0) {
            // 设置坐标轴区间大小
            initScaling(mAxisData.getMinimum(), mAxisData.getMaximum(),
                    dotFigureDataArray.get(0).getValue().size(), mAxisData);
        }
    }

    /**
     * 收敛X轴
     *
     * @param barLineCurveData X轴数据
     */
    public void convergence(ArrayList<T> barLineCurveData) {
        scaleTimes++;
        int count = 0;
        int newCount = 0;
        //二次处理字符过长
        while ((mAxisData.getInterval() * count + mAxisData.getMinimum()) <= mAxisData.getMaximum()) {
            count++;
        }
        float stringLength = paint.measureText(numberFormat.format(mAxisData.getInterval() + mAxisData.getMinimum()));
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
