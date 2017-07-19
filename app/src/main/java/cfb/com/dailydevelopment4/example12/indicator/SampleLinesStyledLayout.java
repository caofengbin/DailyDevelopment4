package cfb.com.dailydevelopment4.example12.indicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import cfb.com.dailydevelopment4.R;
import cfb.com.dailydevelopment4.example12.library.LinePageIndicator;

public class SampleLinesStyledLayout extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themed_lines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (LinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}