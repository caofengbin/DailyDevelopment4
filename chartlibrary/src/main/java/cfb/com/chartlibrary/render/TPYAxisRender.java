package cfb.com.chartlibrary.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.text.NumberFormat;

import cfb.com.chartlibrary.interfaces.iData.IXAxisData;
import cfb.com.chartlibrary.interfaces.iData.IYAxisData;

/**
 * 可视化图表控件 -> Y轴坐标轴的渲染类
 * Created by fengbincao on 2017/7/9.
 */

public class TPYAxisRender extends TPAxisRender {

    private Paint mPaint = new Paint();
    private Paint linePaint = new Paint();

    // 坐标图中的数据信息
    private IYAxisData yAxisData;
    private IXAxisData xAxisData;

    private NumberFormat numberFormat;
    private PointF mPoint = new PointF();

    public TPYAxisRender(IYAxisData yAxisData, IXAxisData xAxisData) {
        super();
        this.yAxisData = yAxisData;
        this.xAxisData = xAxisData;

        mPaint.setAntiAlias(true);
        mPaint.setColor(yAxisData.getColor());
        mPaint.setTextSize(yAxisData.getTextSize());
        mPaint.setStrokeWidth(yAxisData.getPaintWidth());

        // 绘制Y轴横线部分的 paint
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);

        // 设置小数点位数
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(yAxisData.getDecimalPlaces());
    }

    @Override
    public void drawGraph(Canvas canvas) {
        // 步骤1：绘制 Y 轴坐标轴的直线部分
        canvas.drawLine(0, 0, 0, yAxisData.getAxisLength(), mPaint);

        for (int i = 0; (yAxisData.getInterval() * i + yAxisData.getMinimum()) <= yAxisData.getMaximum(); i++) {
            canvas.save();
            canvas.scale(1, -1);

            // 绘制 Y 轴坐标数值
            float textPathX = yAxisData.getAxisLength() / 100;
            float textPathY = (mPaint.descent() + mPaint.ascent()) / 2 +  (yAxisData.getInterval() * i * yAxisData.getAxisScale());
            mPoint.x = -textPathX;
            mPoint.y = -textPathY;

            // 步骤2：绘制 Y 轴坐标轴的指标文字
            textCenter(new String[]{numberFormat.format(yAxisData.getInterval() * i + yAxisData.getMinimum())},
                    mPaint, canvas, mPoint, Paint.Align.RIGHT);

            // 步骤3：绘制 Y 轴表格部分的横线内容(可通过变量设置是否显示)
            if (i > 0) {
                canvas.drawLine(0, -textPathY, xAxisData.getAxisLength(), -textPathY, linePaint);
            }

            canvas.restore();
        }

        // 步骤4：绘制 Y 轴最上面的箭头部分内容
        canvas.drawLine(0, yAxisData.getAxisLength(), yAxisData.getAxisLength() * 0.01f, yAxisData.getAxisLength() * 0.99f, mPaint);
        canvas.drawLine(0, yAxisData.getAxisLength(), -yAxisData.getAxisLength() * 0.01f, yAxisData.getAxisLength() * 0.99f, mPaint);

        canvas.save();
        canvas.scale(1, -1);

        // 步骤5：绘制 Y 轴最上面的单位
        canvas.drawText(yAxisData.getUnit(), 0, -yAxisData.getAxisLength() + (mPaint.descent() + mPaint.ascent()) * 2, mPaint);
        canvas.restore();
    }
}
