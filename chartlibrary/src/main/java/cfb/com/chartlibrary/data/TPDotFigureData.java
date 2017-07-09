package cfb.com.chartlibrary.data;

import android.graphics.PointF;

import java.util.ArrayList;

import cfb.com.chartlibrary.interfaces.iData.IDotFigureData;

/**
 * 可视化图表控件 ->点状图类型的数据接口的实现类
 * 可以包括：柱状图、折线图、曲线图等包含X , Y 轴的表格类型
 * Created by fengbincao on 2017/7/9.
 */

public class TPDotFigureData extends TPChartData implements IDotFigureData {

    /**
     * 点状图类型表格的数据部分
     */
    protected ArrayList<PointF> value;

    public void setValue(ArrayList<PointF> value) {
        this.value = value;
    }

    public ArrayList<PointF> getValue() {
        return value;
    }

}
