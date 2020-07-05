package com.cmt.base.base;

import android.app.Activity;

import java.util.Stack;

/**
 * Date: 2019/12/27
 * Time: 15:31
 * author: cmt
 * desc: 全局activity 管理
 */
public class XActivityManager {
    private static Stack<Activity> activityStack;
    private static final XActivityManager instance = new XActivityManager();
    private XActivityManager(){}
    public static XActivityManager getInstance(){
        return instance;
    }

    /**
     * 获取当前栈中activity的个数
     * @return
     */
    public int getCount(){
        return activityStack.size();
    }
    public void addActivity(Activity activity){
        if(activityStack==null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前栈顶的activity
     */
    public Activity topActivity(){
        if(activityStack!=null&& activityStack.size()>0)
            return activityStack.lastElement();
        return null;
    }

    /**
     * 关闭指定的activity,就是一个activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if(activityStack!=null&& activity!=null){
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 关闭所有的cls对应的activity,无论该activity有多少个实例，都是全部关闭
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        for(Activity activity : activityStack){
            if(activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }

    /**
     * 清空栈中所有的activity
     */
    public void finishAllActivity(){
        for(Activity activity :activityStack){
            activity.finish();
        }
    }

    /**
     * app程序退出
     */
    public void appExit(){
        //清空所有的actiivty
        finishAllActivity();
        //退出JVM,是否占用的内存资源，0表示正常的退出
        System.exit(0);
        //从操作系统中结束掉当前程序线程
        android.os.Process.killProcess(android.os.Process.myPid());
    }



}
