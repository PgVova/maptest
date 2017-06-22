package com.mmh.mapo.ui.screens.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 21.06.17.
 */

public class ViewPagerAdapter extends PagerAdapter{

    FrameLayout distanceFrame;
    FrameLayout priceFrame;
    private final List<FrameLayout> mFramesList = new ArrayList<>();
    private final List<String> mFrameTittleList = new ArrayList<>();

    public ViewPagerAdapter() {
    }

    public Object instantiateItem(ViewGroup container, int position) {
        return mFramesList.get(position);
    }

    @Override
    public int getCount() {
        return mFramesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFrameTittleList.get(position);
    }

    public void addFrame(List<FrameLayout> frames , List<String> titles){
        mFramesList.clear();
        mFrameTittleList.clear();
        mFramesList.addAll(frames);
        mFrameTittleList.addAll(titles);

    }
}
