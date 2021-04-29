package com.example.ncrypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class RateUs extends AppCompatActivity {
    TabLayout tb1;
    ViewPager vp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        tb1 = findViewById(R.id.tb1);
        vp1 = findViewById(R.id.vp1);
        tb1.addTab(tb1.newTab().setText("Rate Us"));
        tb1.addTab(tb1.newTab().setText("About Us"));
        Demo adap = new Demo(this,getSupportFragmentManager(),tb1.getTabCount());
        vp1.setAdapter(adap);
        vp1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb1));
        tb1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}