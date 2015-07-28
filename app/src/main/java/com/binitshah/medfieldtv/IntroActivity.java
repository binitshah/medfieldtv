package com.binitshah.medfieldtv;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

public class IntroActivity extends FragmentActivity {
    ViewPager pager;
    CirclePageIndicator mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator = indicator;
        mIndicator.setViewPager(pager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(4 * density);
        indicator.setPageColor(Color.parseColor("#2c3e50"));
        indicator.setFillColor(Color.parseColor("#2ecc71")); //todo - change this to a better green
        indicator.setStrokeColor(0x00000000);
    }



    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            // since we don't want to get back to the navigational drawer, we'll stifle back here
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Woah there... Let's move forward, yeah?", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Otherwise, select the previous step.
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{
        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int pos) {
            switch(pos) {

                case 0: return Intro_1.newInstance("1");
                case 1: return Intro_2.newInstance("2");
                case 2: return intro_3.newInstance("3");
                case 3: return intro_4.newInstance("4");
                case 4: return intro_5.newInstance("5");
                default: return Intro_1.newInstance("Default");
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
