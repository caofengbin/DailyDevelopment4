package cfb.com.chartlibrary.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.text.NumberFormat;

import cfb.com.chartlibrary.interfaces.iData.IXAxisData;

/**
 * 可视化图表控件 -> X 轴坐标轴的渲染类
 * 柱状图，组合图使用
 * Created by fengbincao on 2017/7/9.
 */

public class TPOffsetXAxisRender extends TPAxisRender {

    private Paint mPaint = new Paint();

    private IXAxisData xAxisData;

    private NumberFormat numberFormat;
    private PointF mPoint = new PointF();

    public TPOffsetXAxisRender(IXAxisData xAxisData) {
        super();
        this.xAxisData = xAxisData;
        // 绘制坐标轴的 paint 的初始化操作
        mPaint.setColor(xAxisData.getColor());
        mPaint.setTextSize(xAxisData.getTextSize());
        mPaint.setStrokeWidth(xAxisData.getPaintWidth());

        //设置小数点位数
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(xAxisData.getDecimalPlaces());
    }

    @Override
    public void drawGraph(Canvas canvas) {
        // 步骤1：绘制 X 轴坐标轴的直线部分
        canvas.drawLine(0, 0, xAxisData.getAxisLength(), 0, mPaint);

        for (int i = 0; (xAxisData.getInterval() * i + xAxisData.getMinimum()) <= xAxisData.getMaximum(); i++) {

            // 步骤2：绘制 X 轴坐标轴的每一个分组向下突出的部分
            canvas.drawLine((xAxisData.getInterval() * (i) * xAxisData.getAxisScale()), 0,
                    (xAxisData.getInterval() * (i) * xAxisData.getAxisScale()),
                    -xAxisData.getAxisLength() / 100, mPaint);

            canvas.save();
            canvas.scale(1, -1);

            // 偏移距离
            canvas.translate(xAxisData.getInterval() * xAxisData.getAxisScale() / 2, 0);

            // 坐标数值
            float TextPathX = (xAxisData.getInterval() * i * xAxisData.getAxisScale());
            float TextPathY = (mPaint.descent() + mPaint.ascent()) - xAxisData.getAxisLength() / 100;
            mPoint.x = TextPathX;
            mPoint.y = -TextPathY;

            // 步骤3：绘制 X 轴坐标轴的每一个分组的分组名称
            textCenter(new String[]{numberFormat.format(xAxisData.getInterval() * i + xAxisData.getMinimum())},
                    mPaint, canvas, mPoint, Paint.Align.CENTER);
            canvas.restore();
        }

        // 步骤4：绘制 X 轴坐标轴的最右边的箭头部分
//        canvas.drawLine(xAxisData.getAxisLength(), 0, xAxisData.getAxisLength() * 0.99f, xAxisData.getAxisLength() * 0.01f, mPaint);
//        canvas.drawLine(xAxisData.getAxisLength(), 0, xAxisData.getAxisLength() * 0.99f, -xAxisData.getAxisLength() * 0.01f, mPaint);

        canvas.save();
        canvas.scale(1, -1);

        // 步骤5：绘制 X 轴坐标轴的最右边的单位字符串
        canvas.drawText(xAxisData.getUnit(), xAxisData.getAxisLength(),
                (mPaint.descent() + mPaint.ascent()) - xAxisData.getAxisLength() / 100, mPaint);
        canvas.restore();
    }
}
