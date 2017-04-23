package com.example.biao.thehealth.health;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Biao on 2017/4/5.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MyAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return new BlankFragment();
    }

    @Override
    public int getCount() {
        return list.size();
    }

/*    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }*/
}