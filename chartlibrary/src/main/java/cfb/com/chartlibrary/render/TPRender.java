package cfb.com.chartlibrary.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * 可视化图表控件 -> 渲染类的基类
 * Created by fengbincao on 2017/7/8.
 */

public abstract class TPRender {

    TPRender() {
        super();
    }

    /**
     * 多行文本居中、居右、居左显示的设置方法
     *
     * @param contentStr    待绘制的文本字符串值
     * @param paint         画笔
     * @param canvas        画布
     * @param point         绘制文字的基准点坐标
     * @param align         居中、居右、居左
     */
    protected void textCenter(String[] contentStr, Paint paint, Canvas canvas, PointF point, Paint.Align align) {
        paint.setTextAlign(align);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int length = contentStr.length;
        float total = (length - 1) * (-top + bottom) + (-fontMetrics.ascent + fontMetrics.descent);
        float offset = total / 2 - bottom;
        for (int i = 0; i < length; i++) {
            float yAxis = -(length - i - 1) * (-top + bottom) + offset;
            canvas.drawText(contentStr[i], point.x, point.y + yAxis, paint);
        }
    }
}
