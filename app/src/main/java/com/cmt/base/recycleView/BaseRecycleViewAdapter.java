package com.cmt.base.recycleView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2019/12/30
 * Time: 16:15
 * author: cmt
 * desc:
 */
public abstract class BaseRecycleViewAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> dataList;
    public BaseRecycleViewAdapter(){
        dataList = new ArrayList<>();
    }
    //baseAdapter只是对list的增删改进行了管理。就是继承baseAdapter的类不需要管理数据，而是服务器业务逻辑的处理和UI的处理


}
