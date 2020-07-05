package com.cmt.base.recycleView;

/* 通用的item bean对象
 * Created by cmt on 2019/10/16
 */
public class RecycleVieItemData<T> {
    private T t;
    //bean的数据类型
    int dataType;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public RecycleVieItemData(T t, int dataType) {
        this.t = t;
        this.dataType = dataType;
    }
}
