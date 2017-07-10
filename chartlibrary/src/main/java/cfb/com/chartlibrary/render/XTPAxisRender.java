package cfb.com.chartlibrary.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.text.NumberFormat;

import cfb.com.chartlibrary.interfaces.iData.IXAxisData;

/**
 * 描述 ; X轴渲染类
 * Created by fengbincao on 2017/7/9.
 */

public class XTPAxisRender extends TPAxisRender {

    private Paint mPaint = new Paint();
    private IXAxisData xAxisData;
    private NumberFormat numberFormat;
    private PointF mPoint = new PointF();

    public XTPAxisRender(IXAxisData xAxisData) {
        super();
        this.xAxisData = xAxisData;
        // 初始化绘图相关数据
        mPaint.setColor(xAxisData.getColor());
        mPaint.setTextSize(xAxisData.getTextSize());
        mPaint.setStrokeWidth(xAxisData.getPaintWidth());
        /**
         * 设置小数点位数
         */
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(xAxisData.getDecimalPlaces());
    }

    @Override
    public void drawGraph(Canvas canvas) {
        canvas.drawLine(0, 0, xAxisData.getAxisLength(), 0, mPaint);

        for (int i = 0; (xAxisData.getInterval() * i + xAxisData.getMinimum()) <= xAxisData.getMaximum(); i++) {
            canvas.drawLine( (xAxisData.getInterval() * i * xAxisData.getAxisScale()), 0,
                     (xAxisData.getInterval() * i * xAxisData.getAxisScale()),
                    -xAxisData.getAxisLength() / 100, mPaint);
            canvas.save();
            canvas.scale(1, -1);
            /**
             * 坐标数值
             */
            float TextPathX =  (xAxisData.getInterval() * i * xAxisData.getAxisScale());
            float TextPathY = (mPaint.descent() + mPaint.ascent()) - xAxisData.getAxisLength() / 100;
            mPoint.x = TextPathX;
            mPoint.y = -TextPathY;
            textCenter(new String[]{numberFormat.format(xAxisData.getInterval() * i + xAxisData.getMinimum())},
                    mPaint, canvas, mPoint, Paint.Align.CENTER);
            canvas.restore();
        }
    }
}
