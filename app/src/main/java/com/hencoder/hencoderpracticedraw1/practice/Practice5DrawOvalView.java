package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice5DrawOvalView extends View {

    private static final String TAG = "Practice5DrawOvalView";

    public Practice5DrawOvalView(Context context) {
        super(context);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawOval() 方法画椭圆

        paint = new Paint();
        paint.setAntiAlias(true);

        int totalWidth = getMeasuredWidth();
        int totalHeight = getMeasuredHeight();

        Log.d(TAG, "onDraw: 测量宽度为:" + totalWidth);
        Log.d(TAG, "onDraw: 测量高度为:" + totalHeight);

        paint.setStyle(Paint.Style.FILL);

        canvas.drawOval(0, 10, totalWidth, totalHeight - 100, paint);
    }
}
