package com.naranya.demonpay.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.naranya.demonpay.R;
import com.naranya.demonpay.adapters.CustomPagerAdapter;
import com.naranya.demonpay.db.DataBaseHelper;

import java.io.IOException;

/**
 * Created by anselmo on 2/3/16.
 */
public class HomeActivity extends BaseActivity {
    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
    }
}
