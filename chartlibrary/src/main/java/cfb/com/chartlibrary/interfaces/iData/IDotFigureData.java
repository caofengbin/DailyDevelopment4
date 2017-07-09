package cfb.com.chartlibrary.interfaces.iData;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * 可视化图表控件 ->点状图类型的数据接口定义
 * 可以包括：柱状图、折线图、曲线图等包含X , Y 轴的表格类型
 * 增加了数据的get与set方法定义
 * Created by fengbincao on 2017/7/9.
 */

public interface IDotFigureData extends IChartData {

    /**
     * 设置图表中的数据
     * @param value     图表数据
     */
    void setValue(ArrayList<PointF> value);

    /**
     * 获取图表数据
     */
    ArrayList<PointF> getValue();
}
