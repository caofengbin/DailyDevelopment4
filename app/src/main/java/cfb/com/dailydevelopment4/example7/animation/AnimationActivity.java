package cfb.com.dailydevelopment4.example7.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

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

    @BindView(R.id.animation_listener_btn)
    public Button mAnimationListenerButton;

    @BindView(R.id.animation_xml_btn)
    public Button mXmlAnimationBtn;

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
    public void btnSet() {
        // 设置使用动画集合
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);

        // 组合使用的第一个动画
        AlphaAnimation aa = new AlphaAnimation(1, 0);
        aa.setDuration(1000);
        as.addAnimation(aa);

        // 组合使用的第二个动画
        TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 200);
        ta.setDuration(1000);
        as.addAnimation(ta);

        mAnimationSetButton2.startAnimation(as);
    }

    @OnClick(R.id.animation_listener_btn)
    public void btnAnimationListener() {
        AlphaAnimation aa = new AlphaAnimation(1, 0);
        aa.setDuration(4000);

        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(AnimationActivity.this, "onAnimationStart", Toast.LENGTH_LONG).show();
                Log.e("test","onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(AnimationActivity.this, "onAnimationEnd", Toast.LENGTH_LONG).show();
                Log.e("test","onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(AnimationActivity.this, "onAnimationRepeat", Toast.LENGTH_LONG).show();
                Log.e("test","onAnimationRepeat");
            }
        });

        aa.setRepeatCount(2);

        mAnimationListenerButton.startAnimation(aa);
    }

    @OnClick(R.id.animation_xml_btn)
    public void btnLoadXmlAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.market_arrow_collapsing_anim);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        mXmlAnimationBtn.startAnimation(anim);
    }
}
