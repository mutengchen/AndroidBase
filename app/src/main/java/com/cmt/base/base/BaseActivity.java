package com.cmt.base.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/*
 * Created by cmt on 2019/12/6
 */
public abstract class BaseActivity extends Activity {

    //加载框，加载失败布局等可以移动baseActivity里面去进行渲染，以viewstub懒加载的方式进行处理，在子类中怎么方便的通知父类执行这个更新ui的方法呢
    public abstract int setLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
    }

    public interface ViewControl{
        void viewLoading();
        void viewError();
        void viewLoadFinished();
    }
}
