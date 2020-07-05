package com.cmt.base.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;


import com.cmt.base.AFrame;

import java.io.File;
import java.util.List;

/**
 * Date: 2019/12/27
 * Time: 10:52
 * author: cmt
 * desc: 与app操作有关的工具类
 */
public class XAppUtils {
    private XAppUtils(){
        throw new UnsupportedOperationException("can not init");
    }
    private static Context context = AFrame.getContext();

    /**
     * 读取application节点获取当前的meta数据
     * @param key
     * @return
     */
    public static String readMetaDataFromApplication(String key){
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据packageName 打开对应的app
     * @param packageName
     */
    public static void startApp(String packageName){
        if(XEmptyUtils.isSpace(packageName))return;
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
    }

    /**
     * 判断app是否有安装过
     * @param packageName
     * @return
     */
    public static boolean isStalledApp(String packageName){
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for(PackageInfo  temp:packageInfos){
            if(temp.packageName.equalsIgnoreCase(packageName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 打开并安装apk
     * @param file
     */
    public static void installApp(File file){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive"); //对应的apk类型在mime_maptype里面对应指定文件名的后缀
        context.startActivity(intent);
    }

    /**
     * 卸载app,根据packageName
     * @param packageName
     */
    public static void uninstallApp(String packageName){
        //指定了intent为删除类型的操作
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:"+packageName));
        context.startActivity(intent);
    }

    /**
     * 获取versionName
     * @return
     */
    public static String getVersionName(){
        return getPackageInfo().versionName;
    }

    /**
     * 获取versionCode
     * @return
     */
    public static int getVersionCode(){
        return getPackageInfo().versionCode;
    }
    public static PackageInfo getPackageInfo(){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 判断是否在前台运行
     * need < uses-permission android:name ="android.permission.GET_TASKS"/>
     * @return
     *
     */
    public static boolean isRunningForeground(){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = activityManager.getRunningTasks(1);
        //判断task是否为空或者没有activity
        if(taskList!=null&&taskList.size()!=0){
            ComponentName componentName = taskList.get(0).topActivity; //获取栈顶的activity
            if(componentName != null && componentName.getPackageName().equalsIgnoreCase(context.getPackageName())){
                return true;
            }
        }
        return false;
    }


    /**
     * 关闭service 服务
     * @param className
     * @return
     */
    public static boolean stopService(String className){
        Intent intent = null;
        boolean ret = false;
        try {
             intent = new Intent(context,Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(intent!=null){
            ret = context.stopService(intent);
        }
        return ret;
    }
    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0   支持4.1.2,4.1.23.4.1.rc111这种形式
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion xloading_error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
}
