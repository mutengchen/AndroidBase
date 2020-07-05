package com.cmt.base.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/* 禁止了左右滑动，如果需要左右滑动的话使用viewPager
 * Created by cmt on 2019/7/16
 */
public class BanSlideViewPager extends ViewPager {
    // false 禁止ViewPager左右滑动。
    private boolean scrollable = false;

    public BanSlideViewPager(@NonNull Context context) {
        super(context);
    }

    public BanSlideViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return scrollable;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }
}
