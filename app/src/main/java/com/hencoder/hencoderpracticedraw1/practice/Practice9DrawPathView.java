package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        path.lineTo(100, 100);                  // 由当前位置 (0, 0) 向 (100, 100) 画一条直线
        path.rLineTo(100, 0);                   // 由当前位置 (100, 100) 向正右方 100 像素的位置画一条直线

        canvas.drawPath(path, paint);
    }
}
