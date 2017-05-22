package cfb.com.dailydevelopment4.example6.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fengbincao on 2017/5/17.
 */

public class DrawTextView extends View {

    private Paint paint;

    public DrawTextView(Context context) {
        super(context);
        initView();
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawTextView(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.BLACK);//颜色
        paint.setStyle(Paint.Style.STROKE);//设置样式
        paint.setAntiAlias(true); //是否使用抗锯齿功能
        paint.setTextSize(50); //设置字体大小
        paint.setTypeface(Typeface.DEFAULT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint.FontMetrics mMetrics = paint.getFontMetrics();
        Log.e("test", "top=" + mMetrics.top);
        Log.e("test", "ascent=" + mMetrics.ascent);
        Log.e("test", "descent=" + mMetrics.descent);
        Log.e("test", "bottom=" + mMetrics.bottom);
        Log.e("test", "leading=" + mMetrics.leading);

        Log.e("test", "getHeight()=" + canvas.getHeight());

        // 文本(要绘制的内容)
        String str = "ABCDEFG";
        paint.measureText(str);

        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str, 200, 500, paint);



        //drawTextByCenter(str,0, 0, canvas, paint );
    }

    public void drawTextByCenter(String text, float x, float y, Canvas canvas, Paint paint) {
        Paint tempPaint = new Paint(paint);             // 创建一个临时画笔,防止影响原来画笔的状态
        tempPaint.setTextAlign(Paint.Align.CENTER);     // 设置文本对齐方式为居中

        // 通过y计算出baseline的位置
        float baseline = y - (tempPaint.descent() + tempPaint.ascent()) / 2;

        canvas.drawText(text, x, baseline, tempPaint);  //绘制文本
    }

}

