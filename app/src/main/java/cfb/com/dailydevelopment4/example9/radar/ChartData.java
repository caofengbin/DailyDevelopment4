package cfb.com.dailydevelopment4.example9.radar;

/**
 * Created by fengbincao on 2017/7/5.
 */

public class ChartData extends BaseData implements IChartData{
    protected String name="";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
