package cfb.com.dailydevelopment4.example8.animation2;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cfb.com.dailydevelopment4.R;

public class AnimationActivity2 extends AppCompatActivity {

    @BindView(R.id.attribute_animation_translation_x)
    public Button mButton1;

    @BindView(R.id.attribute_animation_alpha)
    public Button mButton2;

    @BindView(R.id.attribute_animation_self_define)
    public Button mButton3;

    @BindView(R.id.property_values_holder)
    public Button mButton4;

    @BindView(R.id.use_value_animator)
    public Button mButton5;

    @BindView(R.id.property_animation_listener_btn)
    public Button mButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.attribute_animation_translation_x)
    public void attributeAnimation1() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                mButton1,
                "translationX",
                300
        );

        animator.setDuration(300);
        animator.start();
    }

    @OnClick(R.id.attribute_animation_alpha)
    public void alphaAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                mButton2,
                "alpha",
                0.5f,
                1f
        );

        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    @OnClick(R.id.attribute_animation_self_define)
    public void selfDefineGetAndSetAnimation() {
        WrapperView wrapperView = new WrapperView(mButton3);

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(
                wrapperView,
                "width",
                500
        );

        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    @OnClick(R.id.property_values_holder)
    public void propertyBalueHolderAnimator() {
        PropertyValuesHolder ph1 = PropertyValuesHolder.ofFloat("translationX", 300f);
        PropertyValuesHolder ph2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder ph3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);

        ObjectAnimator.ofPropertyValuesHolder(mButton4, ph1, ph2, ph3)
                .setDuration(1000).start();
    }

    @OnClick(R.id.use_value_animator)
    public void useValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(500);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                Log.e("test","当前值为：" + currentValue);
            }
        });

        valueAnimator.start();
    }

    @OnClick(R.id.property_animation_listener_btn)
    public void propertyAnimationListener() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                mButton2,
                "alpha",
                0.5f,
                1f
        );

        objectAnimator.setDuration(1000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    private static class WrapperView {
        private View mTarget;

        public WrapperView(View target) {
            this.mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
