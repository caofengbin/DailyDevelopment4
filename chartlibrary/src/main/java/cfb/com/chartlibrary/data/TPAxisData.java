package cfb.com.chartlibrary.data;

import cfb.com.chartlibrary.interfaces.iData.IAxisData;

/**
 * 可视化图表控件 -> 坐标轴数据类的实现类
 * Created by fengbincao on 2017/7/9.
 */

public class TPAxisData extends TPChartBaseData implements IAxisData {

    /**
     * (1) 坐标轴长度 属性
     */
    private float axisLength;

    /**
     * (2) 坐标轴最大值 属性
     */
    private float maximum;

    /**
     * (3) 坐标轴最小值 属性
     */
    private float minimum;

    /**
     * (4) 坐标轴区间 属性
     */
    private float interval;

    /**
     * (5) 坐标轴单位 属性
     */
    private String unit = "";

    /**
     * (6) 小数点位数 属性
     */
    private int decimalPlaces;

    /**
     * (7) 坐标轴刻度长度与View长度的比例 属性
     */
    private float axisScale;

    /**
     * 辅助收敛坐标轴
     */
    private float narrowMax;
    private float narrowMin;

    @Override
    public void setAxisLength(float axisLength) {
        this.axisLength = axisLength;
    }

    @Override
    public float getAxisLength() {
        return axisLength;
    }

    @Override
    public void setMaximum(float maximum) {
        this.maximum = maximum;
    }

    @Override
    public float getMaximum() {
        return maximum;
    }

    @Override
    public void setMinimum(float minimum) {
        this.minimum = minimum;
    }

    @Override
    public float getMinimum() {
        return minimum;
    }

    @Override
    public void setInterval(float interval) {
        this.interval = interval;
    }

    @Override
    public float getInterval() {
        return interval;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    @Override
    public void setAxisScale(float axisScale) {
        this.axisScale = axisScale;
    }

    @Override
    public float getAxisScale() {
        return axisScale;
    }

    @Override
    public void setNarrowMax(float narrowMax) {
        this.narrowMax = narrowMax;
    }

    @Override
    public float getNarrowMax() {
        return narrowMax;
    }

    @Override
    public void setNarrowMin(float narrowMin) {
        this.narrowMin = narrowMin;
    }

    @Override
    public float getNarrowMin() {
        return narrowMin;
    }

    @Override
    public String toString() {
        return "TPAxisData{" +
                "axisLength=" + axisLength +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                ", interval=" + interval +
                ", unit='" + unit + '\'' +
                ", narrowMax=" + narrowMax +
                ", narrowMin=" + narrowMin +
                ", decimalPlaces=" + decimalPlaces +
                ", axisScale=" + axisScale +
                '}';
    }
}
