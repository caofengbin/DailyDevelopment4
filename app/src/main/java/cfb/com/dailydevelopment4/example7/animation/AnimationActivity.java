package cfb.com.dailydevelopment4.example7.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cfb.com.dailydevelopment4.R;

/**
 * 学习使用最基本的视图动画框架
 */
public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.alpha_animation_btn)
    public Button mAlphaButton;

    @BindView(R.id.rotation_animation_btn)
    public Button mRotationButton;

    @BindView(R.id.rotation_animation_btn2)
    public Button mRotationButton2;

    @BindView(R.id.translate_animation_btn)
    public Button mTranslateButton;

    @BindView(R.id.scale_animation_btn)
    public Button mScaleButton;

    @BindView(R.id.scale_animation_btn2)
    public Button mScaleButton2;

    @BindView(R.id.animation_set_btn)
    public Button mAnimationSetButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ButterKnife.bind(this);
    }

    /**
     * 透明度动画的效果
     * @param
     */
    @OnClick(R.id.alpha_animation_btn)
    public void btnAlpha() {
        AlphaAnimation aa = new AlphaAnimation(1, 0);
        aa.setDuration(1000);
        aa.setFillAfter(true);
        mAlphaButton.startAnimation(aa);
    }

    @OnClick(R.id.rotation_animation_btn)
    public void btnRotate() {
        RotateAnimation ra = new RotateAnimation(0, 90,
                (float) 0.5 * mRotationButton.getWidth(),
                (float) 0.5 * mRotationButton.getHeight());
        ra.setDuration(1000);
        ra.setFillAfter(true);
        mRotationButton.startAnimation(ra);
    }

    @OnClick(R.id.rotation_animation_btn2)
    public void btnRotate2() {
        RotateAnimation ra = new RotateAnimation(0, 180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(1000);
        ra.setFillAfter(true);
        mRotationButton2.startAnimation(ra);
    }

    @OnClick(R.id.translate_animation_btn)
    public void btnTranslate() {
        TranslateAnimation ta = new TranslateAnimation(400, 200, 400, 300);
        ta.setDuration(1000);
        mTranslateButton.startAnimation(ta);
    }

    @OnClick(R.id.scale_animation_btn)
    public void btnScale() {
        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
        sa.setDuration(1000);
        mScaleButton.startAnimation(sa);
    }

    @OnClick(R.id.scale_animation_btn2)
    public void btnScale2() {
        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F);
        sa.setDuration(1000);
        sa.setFillAfter(true);
        mScaleButton2.startAnimation(sa);
    }

    @OnClick(R.id.animation_set_btn)
    public void btnSet(View view) {
        // 设置使用动画集合
    }
}
