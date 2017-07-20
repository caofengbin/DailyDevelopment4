package cfb.com.dailydevelopment4.example12.indicator;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

import cfb.com.dailydevelopment4.R;
import cfb.com.dailydevelopment4.example12.library.UnderlinePageIndicator;

public class SampleUnderlinesNoFade extends BaseSampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_underlines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        UnderlinePageIndicator indicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFades(false);
        mIndicator = indicator;

        mPager.setCurrentItem(mAdapter.getCount() - 1);

        startScroll();
    }

    private static final int DISPLAY_TIME = 300;
    private Handler hander = new Handler();
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Runnable runable = new Runnable() {
        @Override
        public void run() {
            //true表示平滑滚动
            if(mPager.getCurrentItem() == 0) {
                stopScroll();
            } else {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
            }

        }
    };

    public void startScroll() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                hander.post(runable);
            }
        };
        timer.schedule(timerTask, DISPLAY_TIME, DISPLAY_TIME);
    }

    public void stopScroll() {
        timerTask.cancel();
        timerTask = null;
    }
}