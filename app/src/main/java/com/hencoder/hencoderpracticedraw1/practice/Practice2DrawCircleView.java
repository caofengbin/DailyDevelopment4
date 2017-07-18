package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private static final String TAG = "Practice2DrawCircleView";

    private Paint paint;

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint = new Paint();
        paint.setAntiAlias(true);

        int totalWidth = getMeasuredWidth();
        int totalHeight = getMeasuredHeight();

        Log.d(TAG, "onDraw: 测量宽度为:" + totalWidth);
        Log.d(TAG, "onDraw: 测量高度为:" + totalHeight);

        // 1.第一个圆，绘制实心的圆
        canvas.drawCircle(totalWidth / 4, totalHeight / 4, totalWidth / 8, paint);

        // 2.第二个圆，绘制空心的圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(totalWidth / 4 * 3, totalHeight / 4, totalWidth / 8, paint);

        // 3.第三个圆，绘制一个蓝色实心的圆形
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(totalWidth / 4, totalHeight / 4 * 3, totalWidth / 8, paint);


        // 4.第四个圆形，绘制一个同心圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(40);
        canvas.drawCircle(totalWidth / 4 * 3, totalHeight / 4 * 3, totalWidth / 8, paint);

    }
}
