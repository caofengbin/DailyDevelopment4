package cfb.com.chartlibrary.render;

import android.graphics.Canvas;

/**
 * 描述 ; 图表渲染基类
 * Created by fengbincao on 2017/7/8.
 */

public abstract class ChartRender extends Render {

    public ChartRender() {
        super();
    }

    /**
     * 图表渲染方法
     * @param canvas 画布
     * @param animatedValue 交互动画值
     */
    public abstract void drawGraph(Canvas canvas, float animatedValue);
}
