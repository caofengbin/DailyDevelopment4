package cfb.com.dailydevelopment4.example9.radar;

import java.util.ArrayList;

/**
 * Created by fengbincao on 2017/7/5.
 */

public class RadarData extends ChartData implements IRadarData {
    private ArrayList<Float> value;
    private int alpha = 0x80;

    public void setValue(ArrayList<Float> value) {
        this.value = value;
    }

    public ArrayList<Float> getValue() {
        return value;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getAlpha() {
        return alpha;
    }

}
