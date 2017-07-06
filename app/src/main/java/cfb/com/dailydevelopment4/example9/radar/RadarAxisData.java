package cfb.com.dailydevelopment4.example9.radar;

import android.graphics.Color;

/**
 * Created by fengbincao on 2017/7/5.
 */

public class RadarAxisData extends AxisData implements IRadarAxisData {

    private String[] types;
    private int webColor = Color.GRAY;
    private float[] cosArray;
    private float[] sinArray;

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getTypes() {
        return types;
    }

    public void setWebColor(int webColor) {
        this.webColor = webColor;
    }

    public int getWebColor() {
        return webColor;
    }

    public float[] getCosArray() {
        return cosArray;
    }

    public void setCosArray(float[] cosArray) {
        this.cosArray = cosArray;
    }

    public float[] getSinArray() {
        return sinArray;
    }

    public void setSinArray(float[] sinArray) {
        this.sinArray = sinArray;
    }
}
