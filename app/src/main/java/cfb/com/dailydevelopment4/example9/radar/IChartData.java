package cfb.com.dailydevelopment4.example9.radar;

/**
 * Created by fengbincao on 2017/7/5.
 */

public interface IChartData extends IBaseData{

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
