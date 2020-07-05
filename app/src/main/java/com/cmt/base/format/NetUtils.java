package com.cmt.base.format;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

/*
 * Created by cmt on 2019/12/6
 */
public class NetUtils {
    /**
     *  判断当前网络连接状态处于什么状态
     *  0表示无网络连接
     *  1表示连接4G,3G,2G网络
     *  2表示连接wifi网络
     *  3表示连接wifi网络，但是没有通过wifi密码验证或者连上了wifi但是不能上网
     * @param context
     * @return
     */
    public static boolean isWifiState(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //判断wifi是否连接
        if(wifi.isConnected()){
            return true;
        }
        else{
            return false;
        }
    }

    private static final int NETWORK_NONE = 0;

    private static final int NETWORK_OK = 1;

    /**
     * 判断网络是否可以连接
     * @param context
     * @return
     */
    public static int getNetWorkStates(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isAvailable()) {
            return NETWORK_OK;
        } else {
            return NETWORK_NONE;
        }

    }

    //将整数装换成ip地址
    public static String getIPAddress(int ipInt){
        return (ipInt & 0xFF ) + "." +
                ((ipInt >> 8 ) & 0xFF) + "." +
                ((ipInt >> 16 ) & 0xFF) + "." +
                ( ipInt >> 24 & 0xFF) ;
    }
    public static boolean isInLnRange(String network, String mask) {
        if(mask==null)return false;
        String[] networkips = network.split("\\.");
        int ipAddr = (Integer.parseInt(networkips[0]) << 24)
                | (Integer.parseInt(networkips[1]) << 16)
                | (Integer.parseInt(networkips[2]) << 8)
                | Integer.parseInt(networkips[3]);
        int type = Integer.parseInt(mask.replaceAll(".*/", ""));
        int mask1 = 0xFFFFFFFF << (32 - type);
        String maskIp = mask.replaceAll("/.*", "");
        String[] maskIps = maskIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)
                | (Integer.parseInt(maskIps[1]) << 16)
                | (Integer.parseInt(maskIps[2]) << 8)
                | Integer.parseInt(maskIps[3]);

        return (ipAddr & mask1) == (cidrIpAddr & mask1);
    }
    //判斷wifi ip地址是否在範圍之內
    public static boolean ipAddressIsInRange(String start_ip,String end_ip,String targetIP){
        long start = IP2Long(start_ip);
        long end = IP2Long(end_ip);
        long target = IP2Long(targetIP);
        Log.i("ipAddressIsInRange","start="+start_ip+",target="+targetIP+"end="+end_ip);
        if(target>=start&&target<=end) {
            return true;
        }
        return false;
    }
    //将ip地址转换成整数
    public static long IP2Long(String ipaddress) {
        long result = 0;

        String[] ipAddressInArray = ipaddress.split("\\.");

        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return (long)result;
    }

    //获取当前连接的wifi的ip地址，8.0以上需要权限才能访问
    public static String getConnectWifiIP(Context context){
        WifiManager wifiManager = (WifiManager)context.getSystemService(Activity.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return getIPAddress(wifiInfo.getIpAddress());
    }
}
