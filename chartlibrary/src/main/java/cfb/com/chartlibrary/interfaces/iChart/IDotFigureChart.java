package cfb.com.chartlibrary.interfaces.iChart;

/**
 * 可视化图表控件 -> 点状图类型的行为定义
 * 柱状图、折线图、曲线图绘制接口
 * 该类型图的核心特点，定位方法通过二维的点进行，并且有X,Y坐标轴
 * Created by fengbincao on 2017/7/9.
 */

public interface IDotFigureChart {

    /**
     * 计算 X 轴最大值、最小值、区间长度
     */
    void computeXAxis();

    /**
     * 计算 Y 轴最大值、最小值、区间长度
     */
    void computeYAxis();

    /**
     * 坐标轴字符大小设置
     */
    void setAxisTextSize(float axisTextSize);

    /**
     * 坐标轴颜色设置
     */
    void setAxisColor(int axisColor);

    /**
     * 坐标轴宽度
     */
    void setAxisWidth(float axisWidth);

    /**
     * X坐标轴单位设置
     * @param XAxisUnit     单位字符串
     */
    void setXAxisUnit(String XAxisUnit);

    /**
     * Y坐标轴单位设置
     * @param YAxisUnit     单位字符串
     */
    void setYAxisUnit(String YAxisUnit);
}
