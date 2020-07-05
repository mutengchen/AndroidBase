package com.cmt.base.recycleView;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cmt.base.R;

import java.util.List;

/**
 * Date: 2019/12/30
 * Time: 16:32
 * author: cmt
 * desc: 万能recycleView adapter 同时包含了头部和尾部布局
 */
public abstract class XRecycleViewAdapter<T> extends BaseRecycleViewAdapter<T,XViewHolder> {

    private static int TYPE_HEADER = 0x100;
    private static int TYPE_FOOTER = 0x200;
    private static final int TYPE_LOAD_FAILED = 0x1;
    private static final int TYPE_NO_MORE = 0x2;
    private static final int TYPE_LOAD_MORE = 0x3;
    private static final int TYPE_NO_VIEW = 0x4;
    //用来存在头部和尾部的集合，效率比map高
    private SparseArray<View> mHeaderViews= new SparseArray<>();
    private SparseArray<View> mFooterViews= new SparseArray<>();
    View loadingView;
    View loadingFailedView;
    View noMoreView;
    private RecyclerView recyclerView;
    private Context context;
    public OnLoadMoreListener onLoadMoreListener;
    private int layoutId;
    private boolean isLoadError = false; //标记是否加载出错了
    private boolean isHaveStatesView = false; //是否有加载更多
    private int mLoadItemType = TYPE_NO_VIEW;
    private LayoutInflater inflater;

    @NonNull
    @Override
    public XViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //加载头部的viewholder
        if(isFooterViewType(viewType)){
            return new XViewHolder(mFooterViews.get(viewType));
        }
        //加载尾部的viewholder
        if(isHeaderViewType(viewType)){
            return new XViewHolder(mHeaderViews.get(viewType));
        }
        //【没有更多】的布局
        if(viewType == TYPE_NO_MORE){
            noMoreView = inflater.inflate(R.layout.adapter_no_more,recyclerView,false);
            return new XViewHolder(noMoreView);
        }
        //【正在加载中】的布局
        if(viewType == TYPE_LOAD_MORE){
            loadingView = inflater.inflate(R.layout.adapter_loading,recyclerView,false);
            return new XViewHolder(loadingView);
        }

        //黄油刀绑定

        //【加载失败】的布局
        if(viewType == TYPE_LOAD_FAILED){
            loadingFailedView = inflater.inflate(R.layout.adapter_loading_failed,recyclerView,false);
            return new XViewHolder(loadingFailedView);
        }
        return new XViewHolder(inflater.inflate(viewType,parent,false));
    }

    public XRecycleViewAdapter(RecyclerView recyclerView, List<T> dataList, int layoutId) {
        this.recyclerView = recyclerView;
        this.layoutId = layoutId;
        this.dataList = dataList;
        this.context = recyclerView.getContext();
        this.inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getItemViewType(int position) {
        //头部布局
        if(isHeaderPosition((position))){
            return mHeaderViews.keyAt(position);
        }
        if(isLoadPosition(position)){
            return mLoadItemType;
        }
        if(isFooterPosition(position)){
            position = position - getHeaderCount() - getDataCount();
            return mFooterViews.keyAt(position);
        }
        return super.getItemViewType(position);
    }
    private boolean isFooterViewType(int viewType) {
        int position = mFooterViews.indexOfKey(viewType);
        return position >= 0;
    }

    private boolean isHeaderViewType(int viewType) {
        int position = mHeaderViews.indexOfKey(viewType);
        return position >= 0;
    }
    @Override
    public void onBindViewHolder(@NonNull XViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public abstract void bindData(XViewHolder viewHolder,T data,int position); //itemd的对象，数据和位置，返回给调用者自己去处理,不在adapter里面进行业务逻辑的处理，解耦呀

    public interface OnLoadMoreListener{
        void retry();
        void onLoadMore();
    }
    private boolean isHeaderPosition(int position) {
        return position < getHeaderCount();
    }
    public int getHeaderCount() {
        return mHeaderViews.size();
    }
    private boolean isLoadPosition(int position) {
        return position == getItemCount() -1 && isHaveStatesView;
    }
    private boolean isFooterPosition(int position) {
        return position >= (getHeaderCount() + getDataCount());
    }
    public int getDataCount() {
        return dataList.size();
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //当布局为gridManager的时候，item只为1，出现不居中的情况
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager){   // 布局是GridLayoutManager所管理
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果是Header、Footer的对象则占据spanCount的位置，否则就只占用1个位置
                    return (isHeaderPosition(position)||isFooterPosition(position)) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }
}
