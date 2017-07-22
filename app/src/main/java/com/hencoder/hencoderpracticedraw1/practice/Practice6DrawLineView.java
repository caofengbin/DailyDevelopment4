package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice6DrawLineView extends View {

    private static final String TAG = "Practice6DrawLineView";

    public Practice6DrawLineView(Context context) {
        super(context);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawLine() 方法画直线

        paint = new Paint();
        paint.setAntiAlias(true);

        int totalWidth = getMeasuredWidth();
        int totalHeight = getMeasuredHeight();

        Log.d(TAG, "onDraw: 测量宽度为:" + totalWidth);
        Log.d(TAG, "onDraw: 测量高度为:" + totalHeight);

        paint.setStrokeWidth(20);
        canvas.drawLine(100, 100, 800, 500, paint);
    }
}
