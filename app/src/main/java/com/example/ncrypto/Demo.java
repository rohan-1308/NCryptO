package com.example.ncrypto;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Demo extends FragmentPagerAdapter {
    int count;
    Context c;
    Demo(Context c, FragmentManager fm, int count){
        super(fm);
        this.c = c;
        this.count = count;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                Ratings ob1 = new Ratings();
                return ob1;
            case 1:
                About ob2 = new About();
                return ob2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return count;
    }
}

