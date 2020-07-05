package com.cmt.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* 排序控件
 *1.可以输入自定义排序字段数组，List<String>
 *2.传入数组，排序后返回数组，需要自己去调用datasetChange去更新数据
 *3.如果是字段数组超过屏幕宽度，需要设配成scrollView
 * Created by cmt on 2019/12/6
 */
public class SortView extends LinearLayout {
    public SortView(Context context) {
        super(context);
    }

    public SortView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
