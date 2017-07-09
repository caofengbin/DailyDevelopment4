package cfb.com.chartlibrary.render;

import android.graphics.Canvas;

/**
 * 可视化图表控件 ->渲染类第二层抽象类定义
 * 增加绘图方法)
 * Created by fengbincao on 2017/7/8.
 */

public abstract class TPChartRender extends TPRender {

    public TPChartRender() {
        super();
    }

    /**
     * 图表渲染方法
     *
     * @param canvas        画布
     * @param animatedValue 交互动画值
     */
    public abstract void drawGraph(Canvas canvas, float animatedValue);
}
