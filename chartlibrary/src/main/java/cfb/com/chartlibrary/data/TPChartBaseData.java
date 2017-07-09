package cfb.com.chartlibrary.data;

import android.graphics.Color;

import cfb.com.chartlibrary.interfaces.iData.IBaseData;

/**
 * 可视化图表控件 -> 数据类型的实现类基类
 * Created by fengbincao on 2017/7/9.
 */

public class TPChartBaseData implements IBaseData {

    /**
     * 设置方法1的默认属性：是否显示Y轴的当前值
     * 默认为显示其值的状态
     */
    protected boolean shouldShowYAxisValue = true;

    /**
     * 设置方法2的默认属性：图表的颜色
     * 默认为 black 色
     */
    protected int color = Color.BLACK;

    /**
     * 设置方法3的默认属性：图表上的文字字体大小
     * 默认为30
     */
    protected float textSize = 30;

    /**
     * 设置方法4的默认属性：画笔的宽度值
     * 默认为1
     */
    protected float paintWidth = 1;

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    @Override
    public float getTextSize() {
        return textSize;
    }

    @Override
    public void setShowYAxisValue(boolean showYAxisValue) {
        this.shouldShowYAxisValue = showYAxisValue;
    }

    @Override
    public boolean getShowYAxisValue() {
        return shouldShowYAxisValue;
    }

    @Override
    public void setPaintWidth(float paintWidth) {
        this.paintWidth = paintWidth;
    }

    @Override
    public float getPaintWidth() {
        return paintWidth;
    }
}
