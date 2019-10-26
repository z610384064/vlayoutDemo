package com.example.administrator.myapplication.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PriceFragmentViewpagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    protected String[] mTitles;
    public PriceFragmentViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public PriceFragmentViewpagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public PriceFragmentViewpagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] mTitles) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        this.fragments = fragmentList;
        this.mTitles = mTitles;
    }
    @Override
    public Fragment getItem(int arg0) {
        if (arg0 >= fragments.size()) {
            return null;
        }
        return fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        if (fragments.contains(object)) {
            return POSITION_UNCHANGED;
        }
        return POSITION_NONE;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);

    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
