package cfb.com.chartlibrary.data;

import cfb.com.chartlibrary.interfaces.iData.IChartData;

/**
 * 可视化图表控件 -> 数据类型的第二层数据接口类的实现类
 * Created by fengbincao on 2017/7/9.
 */

public class TPChartData extends TPChartBaseData implements IChartData {

    /**
     * 第二层数据接口类方法中，新增加字段的默认值
     * 默认表名为空
     */
    protected String name = "";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
