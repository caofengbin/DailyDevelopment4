package cfb.com.dailydevelopment4.example9.radar;

import android.graphics.Canvas;

/**
 * Created by fengbincao on 2017/7/5.
 */

public abstract class AxisRender extends Render {
    public AxisRender() {
        super();
    }

    /**
     * 坐标轴渲染方法
     * @param canvas 画布
     */
    public abstract void drawGraph(Canvas canvas);
}
