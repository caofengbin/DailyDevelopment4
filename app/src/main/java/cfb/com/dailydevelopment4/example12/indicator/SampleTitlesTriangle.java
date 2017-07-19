package cfb.com.dailydevelopment4.example12.indicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import cfb.com.dailydevelopment4.R;
import cfb.com.dailydevelopment4.example12.library.TitlePageIndicator;


public class SampleTitlesTriangle extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(TitlePageIndicator.IndicatorStyle.Triangle);
        mIndicator = indicator;
    }
}