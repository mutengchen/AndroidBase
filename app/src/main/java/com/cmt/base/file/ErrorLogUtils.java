package com.cmt.base.file;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 记录api请求日志
 */
public class ErrorLogUtils {
    public static void setCrashLog(String err){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator
                + "MMA_ERROR_LOG/Crash"+ File.separator + df.format(date) + "_crashf.log");
        File pfile = file.getParentFile();
        if(!pfile.exists()) {
            pfile.mkdirs();
        }
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            PrintStream fos = new PrintStream (new FileOutputStream(file, true));
            fos.println(df2.format(date)+"  "+err);
//            e.printStackTrace(fos);
            fos.flush();
            fos.close();
        } catch (IOException ex) {
        }
    }

    public static void setLog(String err){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator
                + "MMA_ERROR_LOG/MMA_API_LOG"+ File.separator + df.format(date) + "_api_log.txt");
        File pfile = file.getParentFile();
        if(!pfile.exists()) {
            pfile.mkdirs();
        }
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            PrintStream fos = new PrintStream (new FileOutputStream(file, true));
            fos.println();
            fos.println(df2.format(date)+err);
//            e.printStackTrace(fos);
            fos.flush();
            fos.close();
        } catch (IOException ex) {
        }
    }
    public static void setMRALog(String error_msg){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator
                + "MMA_ERROR_LOG/MRA_API_LOG"+ File.separator + df.format(date) + "_api_log.txt");
        File pfile = file.getParentFile();
        if(!pfile.exists()) {
            pfile.mkdirs();
        }
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            PrintStream fos = new PrintStream (new FileOutputStream(file, true));
            fos.println();
            fos.println(df2.format(date)+error_msg);
            fos.flush();
            fos.close();
        } catch (IOException ex) {
        }
    }
    //获取exceptionde原因
    public static String getprintStackInfo(Exception e){

        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();
            String[] message = sw.toString().split("\n");
            //写入日志
            for(String temp:message){
                ErrorLogUtils.setCrashLog(temp);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return "";
    }
}
