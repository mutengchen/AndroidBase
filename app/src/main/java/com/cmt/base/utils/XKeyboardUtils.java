package com.cmt.base.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Date: 2019/12/30
 * Time: 14:28
 * author: cmt
 * desc: 键盘相关工具类
 */
public class XKeyboardUtils {

    /**
     * 关闭activity中的键盘
     *
     */
    public static void closeKeyBoard(Activity activity){
        View view = activity.getWindow().peekDecorView();
        if(view!=null){
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    /**
     *  关闭dialog中的键盘
     */
    public static void closeKeyBoard(Dialog dialog){
        View view = dialog.getWindow().peekDecorView();
        if(view!=null){
            InputMethodManager inputMethodManager = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }

    }

    /**
     * 拷贝文本到剪切板上
     * @param context
     * @param text
     */
    public static void clip(Context context,String text){
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.HONEYCOMB){
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(text);
        }else{
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText("content",text));
        }
    }
    //切换键盘的隐藏和显示
    public static void toggleKeyBoard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isActive()){
            inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
