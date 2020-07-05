package com.cmt.base.action;

import android.graphics.Rect;
import android.os.Looper;
import android.view.TouchDelegate;
import android.view.View;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;


/* 交互事件工具类
 * Created by cmt on 2019/12/6
 */
public class ClickUtils {
    /**
     * 扩大View的触摸和点击响应范围,最大不超过其父View范围
     *注意：同一个parent里面的多个view同时用这个方法，只有最后一个生效
     * @param view
     * @param top
     * @param bottom
     * @param left
     * @param right
     */
    public static void expandViewTouchDelegate(final View view, final int top,
                                               final int bottom, final int left, final int right) {

        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    public static void setOnClickListeners(final Action1<View> action,View... views){
        for(View view:views){
            ClickUtils.onClick(view).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Consumer<View>() {
                @Override
                public void accept(View view) throws Exception {
                    action.onClick(view);
                }
            });
        }
    }

    /**
     * 监听onclick事件防抖动
     *
     * @param view
     * @return
     */
    @CheckResult
    @NonNull
    private static Observable<View> onClick(@NonNull View view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewClickOnSubscribe(view));
    }
    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    //这是一个把view封装成一个observable，通过发射器发送出去，如果有建立订阅关系的订阅者就可以收到
    private static class ViewClickOnSubscribe implements ObservableOnSubscribe<View> {
        private View view;

        public ViewClickOnSubscribe(View view) {
            this.view = view;
        }

        @Override
        public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<View> e) throws Exception {
            //检查是否在UI线程中
            checkUiThread();
            //点击事件的处理逻辑，这里想到与发送信息
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!e.isDisposed()) {
                        e.onNext(view);
                    }
                }
            };

            //view与监听器绑定
            view.setOnClickListener(listener);
        }
    }
        public interface Action1<T>{
            void onClick(T t);
        }

        //检查是否在ui线程中
    public static void checkUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException(
                    "Must be called from the main thread. Was: " + Thread.currentThread());
        }
    }
}
