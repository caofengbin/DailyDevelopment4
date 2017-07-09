package cfb.com.chartlibrary.render;

import android.graphics.Canvas;

/**
 * 描述：坐标轴渲染基类
 * Created by fengbincao on 2017/7/9.
 */

public abstract class AxisTPRender extends TPRender {

    public AxisTPRender() {
        super();
    }

    /**
     * 坐标轴渲染方法
     * @param canvas 画布
     */
    public abstract void drawGraph(Canvas canvas);

}
