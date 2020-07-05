package com.cmt.base.recycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

/* 多item布局的设配器
 * Created by cmt on 2019/10/16
 */

/**如何使用
 * 添加测试数据
        List<RecycleVieItemData> list = new ArrayList<>();
        list.add(new RecycleVieItemData(new Item1("item1_1"),0));
        list.add(new RecycleVieItemData(new Item2("item2_1"),1));
        list.add(new RecycleVieItemData(new Item1("item1_2"),0));
        list.add(new RecycleVieItemData(new Item2("item2_2"),1));
        //创建bean对应的item layout_id
        HashMap<Integer,Integer> layout_map = new HashMap<>();
        layout_map.put(0,R.layout.item1_layout);
        layout_map.put(1,R.layout.item2_layout);
        //创建设配器
        DiffItemAdapter diffItemAdapter = new DiffItemAdapter(this,list,layout_map) {
        @Override
        public void bindData(CommonViewHolder holder, RecycleVieItemData data, int viewType, int position) {
        //处理数据了
        switch (viewType){
        case 0:
        dealHoldItem1(holder,(Item1)data.getT());
        break;
        case 1:
        dealHoldItem2(holder,(Item2)data.getT());
        }
        }
        };
 */
public abstract class DiffItemAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    //数据列表
    private List<RecycleVieItemData> dataList;
    //类型列表
    private HashMap<Integer, Integer> itemMap;
    private Context context;
    public DiffItemAdapter(Context context, List<RecycleVieItemData> dataList, HashMap<Integer, Integer> map) {
        this.dataList = dataList;
        this.itemMap = map;
        this.context = context;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据viewType来判断
        if(itemMap!=null){
            //获取viewType对应的layoutId
            if(itemMap.get(viewType)!=null){
                //加载item控件
                View view = LayoutInflater.from(context).inflate(itemMap.get(viewType),parent,false);
                return new CommonViewHolder(view,viewType);

            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setIsRecyclable(false);//不复用了，会造成item的刷新乱
        bindData(holder,dataList.get(position),holder.getViewType(),position);
    }

    @Override
    public int getItemViewType(int position) {
        //根据数据类型的不同返回其类型给onCreateViewHolder
        return dataList.get(position).getDataType();
    }

    @Override
    public int getItemCount() {
        return dataList==null? 0:dataList.size();
    }
    public abstract void bindData(CommonViewHolder holder,RecycleVieItemData data,int viewType,int position);
}
