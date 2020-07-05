package com.cmt.base.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/*
 * Created by cmt on 2019/12/6
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {
    public List<Fragment> fragments;
    private FragmentManager fm;
    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        fragments = fragmentList;
        this.fm = fm;
    }

    public void updateData(List<Fragment> list){
        if(list==null)return;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments==null?null:fragments.get(position);
    }



    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public long getItemId(int position) {
        return fragments.get(position).hashCode();
    }

    @Override
    public int getCount() {
        return fragments==null?0:fragments.size();
    }
}
