package cfb.com.chartlibrary.interfaces.iData;

/**
 * 可视化图表控件 -> 数据接口类的第二层接口定义
 * 增加表格名称
 * Created by fengbincao on 2017/7/8.
 */

public interface IChartData extends IBaseData {

    /**
     * 设置图表名称
     */
    void setName(String name);

    /**
     * 获取图表名称
     */
    String getName();
}
