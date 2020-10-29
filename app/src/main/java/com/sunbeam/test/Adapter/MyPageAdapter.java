package com.sunbeam.test.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sunbeam.test.fragment.fragment;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        fragment frag = new fragment(i);
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "All";
        else if(position == 1)
            return "TechCrunch";
        else if (position == 2)
            return "Wall Street";
        else
            return "Bitcoin";
    }
}
