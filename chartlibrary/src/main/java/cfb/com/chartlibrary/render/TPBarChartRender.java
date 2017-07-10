package cfb.com.chartlibrary.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.text.NumberFormat;

import cfb.com.chartlibrary.interfaces.iData.IBarData;
import cfb.com.chartlibrary.interfaces.iData.IXAxisData;
import cfb.com.chartlibrary.interfaces.iData.IYAxisData;

/**
 * 可视化图表控件 -> 柱状图类型表格内容渲染类
 * Created by fengbincao on 2017/7/10.
 */

public class TPBarChartRender extends TPChartRender {

    private IBarData barData;
    private Path barPath = new Path();
    private Paint barPaint = new Paint();

    // 柱状图的 X, Y 轴坐标内容
    private IXAxisData xAxisData;
    private IYAxisData yAxisData;

    private float offset;
    private float barWidth;

    private PointF mPointF = new PointF();

    public TPBarChartRender(IBarData barData, IXAxisData xAxisData, IYAxisData yAxisData, float offset, float barWidth) {
        super();

        this.barData = barData;
        this.xAxisData = xAxisData;
        this.yAxisData = yAxisData;
        this.offset = offset;
        this.barWidth = barWidth;

        barPaint.setStyle(Paint.Style.FILL);
        barPaint.setAntiAlias(true);
        barPaint.setStrokeWidth(barData.getPaintWidth());
        barPaint.setTextSize(barData.getTextSize());
    }

    @Override
    public void drawGraph(Canvas canvas, float animatedValue) {
        float currentXAxis, currentYAxis;

        // 为添加更多点准备路径,可以更有效地分配其存储的路径
        barPath.incReserve(barData.getValue().size());

        for (int j = 0; j < barData.getValue().size(); j++) {
            currentXAxis = (barData.getValue().get(j).x - xAxisData.getMinimum()) * xAxisData.getAxisScale() + offset;
            currentYAxis = -(barData.getValue().get(j).y - yAxisData.getMinimum()) * yAxisData.getAxisScale() * animatedValue;

            // 绘制矩形区域
            barPath.addRect(currentXAxis, currentYAxis, currentXAxis + barWidth, 0, Path.Direction.CW);

            // 绘制柱状图顶部的文字内容
            Paint.FontMetrics fontMetrics = barPaint.getFontMetrics();
            mPointF.x = currentXAxis + barWidth / 2;
            mPointF.y = currentYAxis + (fontMetrics.top - fontMetrics.bottom);
            NumberFormat numberFormatY = NumberFormat.getNumberInstance();
            numberFormatY.setMaximumFractionDigits(yAxisData.getDecimalPlaces());
            barPaint.setColor(xAxisData.getColor());

            if (barData.getShowYAxisValue())
                textCenter(new String[]{numberFormatY.format(barData.getValue().get(j).y)}, barPaint, canvas, mPointF,
                        Paint.Align.CENTER);
        }

        // 保存绘图区内容，并设置 柱状图图层为0x80半透明状态
        canvas.saveLayerAlpha(-canvas.getWidth() + xAxisData.getAxisLength(), -yAxisData.getAxisLength(),
                xAxisData.getAxisLength(), canvas.getHeight() - yAxisData.getAxisLength(), 0x80, Canvas.ALL_SAVE_FLAG);

        barPaint.setColor(barData.getColor());
        canvas.drawPath(barPath, barPaint);
        canvas.restore();
        barPath.reset();
    }
}
