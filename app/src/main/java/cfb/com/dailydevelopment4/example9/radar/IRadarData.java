package cfb.com.dailydevelopment4.example9.radar;

import java.util.ArrayList;

/**
 * Created by fengbincao on 2017/7/5.
 */

public interface IRadarData extends IChartData {
    /**
     * 设置雷达图值
     * @param value 数据集合
     */
    void setValue(ArrayList<Float> value);

    /**
     * 获取雷达图值
     * @return 数据集合
     */
    ArrayList<Float> getValue();

    /**
     * 设置雷达图透明度
     * @param alpha 透明度
     */
    void setAlpha(int alpha);

    /**
     * 获取雷达图透明度
     * @return 透明度
     */
    int getAlpha();
}
