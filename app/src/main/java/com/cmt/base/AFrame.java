package com.cmt.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.cmt.base.utils.XDensityUtils;
import com.cmt.base.utils.XOutdatedUtils;


/**
 * Date: 2019/12/27
 * Time: 10:59
 * author: cmt
 * desc: 全局上下文对象管理器
 */
public class AFrame {
    private static Context context;
    public static int screenHeight;
    public static int screenWidth;
    // #log
    public static String tag = "AFrame";
    public static boolean isDebug = true;

    public static void init(Context context) {
        AFrame.context = context;
        screenHeight = XDensityUtils.getScreenHeight();
        screenWidth = XDensityUtils.getScreenWidth();
    }
    public static Context getContext() {
        synchronized (AFrame.class) {
            if (AFrame.context == null)
                throw new NullPointerException("Call AFrame.init(context) within your Application onCreate() method." +
                        "Or extends XApplication");
            return AFrame.context.getApplicationContext();
        }
    }
    public static Resources getResources() {
        return AFrame.getContext().getResources();
    }

    public static String getString(@StringRes int id) {
        return getResources().getString(id);
    }

    public static Resources.Theme getTheme() {
        return AFrame.getContext().getTheme();
    }

    public static AssetManager getAssets() {
        return AFrame.getContext().getAssets();
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        return XOutdatedUtils.getDrawable(id);
    }

    public static int getColor( @ColorRes int id) {
        return XOutdatedUtils.getColor(id);
    }

    public static Object getSystemService(String name){
        return AFrame.getContext().getSystemService(name);
    }

    public static Configuration getConfiguration() {
        return AFrame.getResources().getConfiguration();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return AFrame.getResources().getDisplayMetrics();
    }
}
