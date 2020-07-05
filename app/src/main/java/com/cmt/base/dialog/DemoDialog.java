package com.cmt.base.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cmt.base.R;


/**
 * Date: 2020/5/9
 * Time: 14:18
 * author: cmt
 * desc:
 */
public class DemoDialog extends BaseDialog {
    TextView content;
    public DemoDialog(@NonNull Context context, int themeResId, DialogParams params) {
        super(context, themeResId, params);
        bindChildView(new BindChildView() {
            @Override
            public void startBind(View view) {
                content = findViewById(R.id.content);
                content.setText("我是demo dialog呀，继承了baseDialog");
            }
        });
    }

    @Override
    public int initView() {
        return R.layout.hahaha;
    }
}
