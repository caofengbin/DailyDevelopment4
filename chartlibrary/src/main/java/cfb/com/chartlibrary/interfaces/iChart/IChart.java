package cfb.com.chartlibrary.interfaces.iChart;

/**
 * 可视化图表控件 -> 所有表格类型的基类定义
 * Created by fengbincao on 2017/7/8.
 */

public interface IChart {

    /**
     * 坐标轴字符大小设置
     */
    void setAxisTextSize(float axisTextSize);

    /**
     * 坐标轴颜色设置
     */
    void setAxisColor(int axisColor);

    /**
     * 坐标轴宽度设置
     */
    void setAxisWidth(float axisWidth);
}
