package cfb.com.chartlibrary.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.text.NumberFormat;

import cfb.com.chartlibrary.interfaces.iData.IXAxisData;
import cfb.com.chartlibrary.interfaces.iData.IYAxisData;

/**
 * 描述：Y轴渲染类
 * Created by fengbincao on 2017/7/9.
 */

public class YAxisTPRender extends AxisTPRender {

    private Paint mPaint = new Paint();
    private Paint linePaint = new Paint();
    private IYAxisData yAxisData;
    private IXAxisData xAxisData;
    private NumberFormat numberFormat;
    private PointF mPoint = new PointF();

    public YAxisTPRender(IYAxisData yAxisData, IXAxisData xAxisData) {
        super();
        this.yAxisData = yAxisData;
        this.xAxisData = xAxisData;
        mPaint.setAntiAlias(true);
        mPaint.setColor(yAxisData.getColor());
        mPaint.setTextSize(yAxisData.getTextSize());
        mPaint.setStrokeWidth(yAxisData.getPaintWidth());

        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);

        // 设置小数点位数
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(yAxisData.getDecimalPlaces());
    }

    @Override
    public void drawGraph(Canvas canvas) {
        canvas.drawLine(0, 0, 0, yAxisData.getAxisLength(), mPaint);

        for (int i = 0; (yAxisData.getInterval() * i + yAxisData.getMinimum()) <= yAxisData.getMaximum(); i++) {
            canvas.save();
            canvas.scale(1, -1);

            // 绘制Y轴坐标数值
            float TextPathX = yAxisData.getAxisLength() / 100;
            float TextPathY = (mPaint.descent() + mPaint.ascent()) / 2 +  (yAxisData.getInterval() * i * yAxisData.getAxisScale());
            mPoint.x = -TextPathX;
            mPoint.y = -TextPathY;
            textCenter(new String[]{numberFormat.format(yAxisData.getInterval() * i + yAxisData.getMinimum())},
                    mPaint, canvas, mPoint, Paint.Align.RIGHT);
            if (i > 0)
                canvas.drawLine(0, -TextPathY, xAxisData.getAxisLength(), -TextPathY, linePaint);
            canvas.restore();
        }

        // 绘制顶部的箭头
        canvas.drawLine(0, yAxisData.getAxisLength(), yAxisData.getAxisLength() * 0.01f, yAxisData.getAxisLength() * 0.99f, mPaint);
        canvas.drawLine(0, yAxisData.getAxisLength(), -yAxisData.getAxisLength() * 0.01f, yAxisData.getAxisLength() * 0.99f, mPaint);
        canvas.save();
        canvas.scale(1, -1);
        canvas.drawText(yAxisData.getUnit(), 0, -yAxisData.getAxisLength() + (mPaint.descent() + mPaint.ascent()) * 2, mPaint);
        canvas.restore();
    }
}
