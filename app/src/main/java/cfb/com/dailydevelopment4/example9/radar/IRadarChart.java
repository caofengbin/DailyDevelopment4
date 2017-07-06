package cfb.com.dailydevelopment4.example9.radar;

/**
 * Created by fengbincao on 2017/7/5.
 */

public interface IRadarChart {

    /**
     * 计算雷达图坐标
     */
    void computeRadar();

    /**
     * 设置各角类型文本颜色
     * @param color 颜色
     */
    void setAxisValueColor(int color);

    /**
     *设置各角文本集合
     * @param types 文本字符串集合
     */
    void setTypes(String[] types);

}
