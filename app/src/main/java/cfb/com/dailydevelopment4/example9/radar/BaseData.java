package cfb.com.dailydevelopment4.example9.radar;

import android.graphics.Color;

/**
 * Created by fengbincao on 2017/7/5.
 */

public class BaseData implements IBaseData {

    protected int color = Color.BLACK;
    protected float paintWidth = 1;
    protected float textSize = 30;
    /**
     * 是否显示图表的Y值
     */
    protected boolean isTextSize = true;

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setIsTextSize(boolean isTextSize){
        this.isTextSize = isTextSize;
    }

    public boolean getIsTextSize(){
        return isTextSize;
    }

    public void setPaintWidth(float paintWidth) {
        this.paintWidth = paintWidth;
    }

    public float getPaintWidth() {
        return paintWidth;
    }
}
