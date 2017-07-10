package cfb.com.chartlibrary.render;

import android.graphics.Canvas;

/**
 * 可视化图表控件 -> 坐标轴的渲染类
 * Created by fengbincao on 2017/7/9.
 */

public abstract class TPAxisRender extends TPRender {

    public TPAxisRender() {
        super();
    }

    /**
     * 坐标轴渲染方法
     * @param canvas 画布
     */
    public abstract void drawGraph(Canvas canvas);

}
