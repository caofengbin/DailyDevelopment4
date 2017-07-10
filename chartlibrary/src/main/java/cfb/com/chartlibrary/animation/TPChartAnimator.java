package cfb.com.chartlibrary.animation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;

/**
 * 可视化图表控件 ->  生成动画帮助类
 * Created by fengbincao on 2017/7/10.
 */

public class TPChartAnimator {

    private ValueAnimator animator = new ValueAnimator();
    private ValueAnimator.AnimatorUpdateListener listener;
    private TimeInterpolator timeInterpolator;

    public TPChartAnimator(ValueAnimator.AnimatorUpdateListener listener, TimeInterpolator timeInterpolator) {
        super();
        this.listener = listener;
        this.timeInterpolator = timeInterpolator;
    }

    public TPChartAnimator(ValueAnimator.AnimatorUpdateListener listener) {
        this(listener, new DecelerateInterpolator());
    }

    /**
     * 使用时，需要 invalidate()
     *
     * @param duration 动画时间
     * @param y        动画运动目标值
     */
    public void animatedY(long duration, float y) {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator.start();
        } else {
            animator = ValueAnimator.ofFloat(0, y).setDuration(duration);
            animator.setInterpolator(timeInterpolator);
            animator.addUpdateListener(listener);
            animator.start();
        }
    }
}