package cfb.com.dailydevelopment4.example9.radar;

import android.graphics.Canvas;

/**
 * Created by fengbincao on 2017/7/5.
 */

public abstract class ChartRender extends Render{

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
