package com.cmt.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * Date: 2020/5/9
 * Time: 9:36
 * author: cmt
 * desc: baseDialog，对外暴露了setContentView，相当于可以自己设置自定义的xml layout
 *       同时配合着DialogParams dialog参数配置，可以实现圆角，背景颜色，布局方式，显示原点的位置（x,y的坐标）等设置
 */
public abstract class BaseDialog extends Dialog {
    View view;
    BindChildView bind;
    DialogParams params;
    public BaseDialog(@NonNull Context context,int themeResId,DialogParams params) {
        super(context,themeResId);
        this.params = params;
        initUi(this.params);
    }

    /**
     * 加载完成后给子类dialog返回一个setContentview好的view
     * 在子类dialog 可以通过view.findbyViewId的方法，,实现控件的代码逻辑
     * @param bind
     */
    public void bindChildView(BindChildView bind){
        this.bind = bind;
        bind.startBind(view);
    }

    private void initUi(DialogParams params){
        setContentView(initView());
        setUiStyle(params);
    }

    /**
     * 根据DialogParams的参数，来绘制dialog的布局
     * @param params
     */
    public void setUiStyle(DialogParams params){
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        if(params.getDialogWidth()!=0 && params.getDialogHeight() !=0 ){
            lp.width = params.getDialogWidth();
            lp.height = params.getDialogHeight();
        }
        //设置窗体的对齐方式，默认是中左对齐
        if(params.getDialogGravity()==0){
            getWindow().setGravity(Gravity.CENTER);
        }else{
            getWindow().setGravity(params.getDialogGravity());
        }
        //设置窗体原点位置，默认是x=0,y=0
        if(params.getLayoutX()!=0){
            lp.x = params.getLayoutX();
        }
        if(params.getLayoutY()!=0){
            lp.y = params.getLayoutY();
        }
        //设置背景圆角
        GradientDrawable gd = new GradientDrawable();
        if(params.getFillColor()!=0){
            gd.setColor(params.getFillColor());
        }
        //设置圆角都是一样的
        if(params.getRoundRadius()!=0){
            gd.setCornerRadius(params.getRoundRadius());
        }
        //设置四个圆角不一样的情况
        if(params.getRoundRadiusArray()!=null){
            gd.setCornerRadii(params.getRoundRadiusArray());
        }
        getWindow().setBackgroundDrawable(gd);
        //更新窗体配置
        win.setAttributes(lp);
    }

    public abstract int initView();

    public interface BindChildView{
        void startBind(View view);
    }
}
