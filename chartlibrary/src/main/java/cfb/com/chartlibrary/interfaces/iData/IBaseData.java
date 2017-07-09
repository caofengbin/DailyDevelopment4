package cfb.com.chartlibrary.interfaces.iData;

/**
 * 可视化图表控件 -> 数据接口类的基类
 * 定义的相关set方法
 * (1) 是否显示Y轴的当前值
 * (2) 图表的颜色值
 * (3) 图表上的文字大小
 * (4) 画笔的宽度
 * Created by fengbincao on 2017/7/8.
 */

public interface IBaseData {

    /**
     * 设置是否显示Y轴的值(例如柱状图的最高点显示当前的值)
     */
    void setShowYAxisValue(boolean isTextSize);

    /**
     * 获取是否显示Y值
     */
    boolean getShowYAxisValue();

    /**
     * 设置图表颜色
     */
    void setColor(int color);

    /**
     * 获取图表颜色
     */
    int getColor();

    /**
     * 设置图表文字大小
     */
    void setTextSize(float textSize);

    /**
     * 获取图表文字大小
     */
    float getTextSize();

    /**
     * 设置画笔宽度
     */
    void setPaintWidth(float paintWidth);

    /**
     * 获取画笔宽度
     */
    float getPaintWidth();
}
