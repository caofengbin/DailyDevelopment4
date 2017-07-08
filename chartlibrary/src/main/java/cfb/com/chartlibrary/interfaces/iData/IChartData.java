package cfb.com.chartlibrary.interfaces.iData;

/**
 * 图表数据基类接口
 * Created by fengbincao on 2017/7/8.
 */

public interface IChartData extends IBaseData {

    /**
     * 设置图表名称
     * @param name 图表名
     */
    void setName(String name);

    /**
     * 获取图表名称
     * @return 图表名
     */
    String getName();
}
