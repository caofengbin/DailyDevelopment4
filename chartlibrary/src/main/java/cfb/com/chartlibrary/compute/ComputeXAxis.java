package cfb.com.chartlibrary.compute;

import android.graphics.Paint;

import java.text.NumberFormat;
import java.util.ArrayList;

import cfb.com.chartlibrary.interfaces.iData.IDotFigureData;
import cfb.com.chartlibrary.interfaces.iData.IXAxisData;

/**
 * Created by Idtk on 2016/6/6.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 * 描述 ; X轴计算类
 */
public class ComputeXAxis<T extends IDotFigureData> extends Compute {

    private IXAxisData xAxisData;
    private NumberFormat numberFormat;
    private Paint paint = new Paint();

    /**
     * 限制收敛次数
     */
    private int times = 0;

    public ComputeXAxis(IXAxisData axisData) {
        super(axisData);
        xAxisData = axisData;
        paint.setColor(xAxisData.getColor());
        paint.setTextSize(xAxisData.getTextSize());
        paint.setStrokeWidth(xAxisData.getPaintWidth());
        //设置小数点位数
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(xAxisData.getDecimalPlaces());
    }

    /**
     * 计算坐标系
     *
     * @param dotFigureDataArray 坐标集合
     */
    public void computeXAxis(ArrayList<T> dotFigureDataArray) {
        for (int i = 0; i < dotFigureDataArray.size(); i++) {
            IDotFigureData dotFigureData = dotFigureDataArray.get(i);
            // 通过 dotFigureData.getValue() 操作取到的才是点集部分的数据
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
                xAxisData.setNarrowMin(minX);
                xAxisData.setNarrowMax(maxX);
            } else {
                xAxisData.setNarrowMin(minX < xAxisData.getNarrowMin() ? minX : xAxisData.getNarrowMin());
                xAxisData.setNarrowMax(maxX > xAxisData.getNarrowMax() ? maxX : xAxisData.getNarrowMax());
            }

            initMaxMin(maxX, minX, i, xAxisData);
        }
        if (dotFigureDataArray.size() > 0) {
            initScaling(xAxisData.getMinimum(), xAxisData.getMaximum(),
                    dotFigureDataArray.get(0).getValue().size(), xAxisData);

        }
    }

    /**
     * 收敛X轴
     *
     * @param barLineCurveData X轴数据
     */
    public void convergence(ArrayList<T> barLineCurveData) {
        times++;
        int count = 0;
        int newCount = 0;
        //二次处理字符过长
        while ((xAxisData.getInterval() * count + xAxisData.getMinimum()) <= xAxisData.getMaximum()) {
            count++;
        }
        float stringLength = paint.measureText(numberFormat.format(xAxisData.getInterval() + xAxisData.getMinimum()));
        while (count * stringLength > xAxisData.getAxisLength()) {
            count = count / 2;
            newCount++;
        }
        xAxisData.setInterval(newCount != 0 ? xAxisData.getInterval() * newCount * 2 : xAxisData.getInterval());
        //收敛
        while (xAxisData.getNarrowMin() - xAxisData.getMinimum() > xAxisData.getInterval()) {
            xAxisData.setMinimum(xAxisData.getMinimum() + xAxisData.getInterval());
        }

        while (xAxisData.getMaximum() - xAxisData.getNarrowMax() > xAxisData.getInterval()) {
            xAxisData.setMaximum(xAxisData.getMaximum() - xAxisData.getInterval());
        }

        if (xAxisData.getMaximum() - xAxisData.getMinimum() <= (xAxisData.getInterval() * 2) && times < 5) {
            initScaling(xAxisData.getMinimum(), xAxisData.getMaximum(), barLineCurveData.get(0).getValue().size(), xAxisData);
            convergence(barLineCurveData);
        }
    }
}
