package cfb.com.dailydevelopment4.example12.indicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import cfb.com.dailydevelopment4.R;
import cfb.com.dailydevelopment4.example12.library.CirclePageIndicator;

public class SampleCirclesStyledTheme extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The look of this sample is set via a style in the manifest
        setContentView(R.layout.simple_circles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}