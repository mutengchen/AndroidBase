package com.cmt.base.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;

import com.cmt.base.R;


/**
 * Date: 2019/12/30
 * Time: 15:35
 * author: cmt
 * desc: 状态栏工具类
 */
public class XStatusBarUtils {
    public static final int DEFAULT_STATUS_BAR_ALPHA = 112;
    public static final int FAKE_STATUS_BAR_VIEW_ID = R.id.xstatusbar_fake_status_bar_view;
    public static final int FAKE_TRANSLUCENT_VIEW_ID = R.id.xstatusbar_translucent_view;

    //设置状态栏颜色
    public static void setColor(Activity activity, @ColorInt int color, int statusAlpha) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(color);
        }
    }

    /**
     * 计算状态栏颜色
     */
    public static int calStatusBarColor(@ColorInt int color, int alpha) {
        if (alpha == 0) return color;
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;

    }

    //获取状态栏的高度
    public static int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resId);
    }

    //设置透明的状态栏
    private static StatusBarView createTranslucentStatusBarView(Activity activity, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
        statusBarView.setId(FAKE_TRANSLUCENT_VIEW_ID);
        return statusBarView;
    }

    public static class StatusBarView extends View {
        public StatusBarView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public StatusBarView(Context context) {
            super(context);
        }
    }
}
