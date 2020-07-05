package com.cmt.base.recycleView;

import android.content.Context;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


/*
 * Created by cmt on 2019/11/20
 */
public class RecycleViewItemDecoration {
    public static RecyclerView.ItemDecoration getRecyclerViewDivider(Context context, @DrawableRes int drawableId) {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(context.getResources().getDrawable(drawableId));
        return itemDecoration;
    }
}
