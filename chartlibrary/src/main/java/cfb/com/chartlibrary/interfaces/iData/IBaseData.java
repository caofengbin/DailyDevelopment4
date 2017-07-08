package cfb.com.chartlibrary.interfaces.iData;

/**
 * 描述:所有数据类接口
 * 包括：图标文字大小，图表颜色，画笔宽度，是否显示Y值四个相关的设置
 * Created by fengbincao on 2017/7/8.
 */

public interface IBaseData {

    /**
     * 设置是否显示Y值
     */
    void setIsTextSize(boolean isTextSize);

    /**
     * 获取是否显示Y值
     */
    boolean getIsTextSize();

    /**
     * 设置图表颜色
     */
    void setColor(int color);

    /**
     * 获取图表颜色
     * @return 颜色
     */
    int getColor();

    /**
     * 设置图表文字大小
     * @param textSize 文字大小
     */
    void setTextSize(float textSize);

    /**
     * 获取图表文字大小
     * @return 文字大小
     */
    float getTextSize();

    /**
     * 设置画笔宽度
     * @param paintWidth 画笔宽度
     */
    void setPaintWidth(float paintWidth);

    /**
     * 获取画笔宽度
     * @return 画笔宽度
     */
    float getPaintWidth();
}
