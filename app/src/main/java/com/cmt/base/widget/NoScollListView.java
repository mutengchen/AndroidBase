package com.cmt.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Date: 2019/12/27
 * Time: 14:35
 * author: cmt
 * desc: listView 不设置滚动
 */
public class NoScollListView  extends ListView {
    public NoScollListView(Context context) {
        super(context);
    }

    public NoScollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandedSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandedSpec);
    }
}
