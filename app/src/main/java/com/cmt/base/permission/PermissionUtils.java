package com.cmt.base.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/* app权限工具类
 * Created by cmt on 2019/12/6
 */
public class PermissionUtils {

    //一次检查和获取所有权限
    public static void requestPermisson(Context context, String[] permissions){
        List<String> permissionList = new ArrayList<>();
        //获取没有授权过的权限
        for(String permission:permissions){
            if(ContextCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission);
            }
        }
        if(!permissionList.isEmpty()){
            ActivityCompat.requestPermissions((Activity) context,permissionList.toArray(new String[permissionList.size()]),1);
        }else{
            Log.i("permisson","all granted");
        }
    }

    //获取单个权限的授权状况
    public static boolean isPermissionOpen(Context context,String permission){
        if(ContextCompat.checkSelfPermission(context,permission)!=PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    //打开权限设置页面
    public void goIntentSetting(Context context){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        Uri uri = Uri.fromParts("package",context.getPackageName(),null);
        intent.setData(uri);
        try{
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
