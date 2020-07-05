package com.cmt.base.action;

import android.content.Context;
import android.widget.Toast;


/* toast,dilaog提示控件
 * Created by cmt on 2019/7/5
 */
public class AlertUtils {
//    private static Dialog loadingDialog;
//    public static Dialog getLoadingDialogInstance(Context context){
//        if(loadingDialog==null)
//            loadingDialog = new Dialog(context, R.style.Dialog);
//        return loadingDialog;
//    }
//    public static void loadDissmiss(){
//        if(loadingDialog!=null){
//            loadingDialog.dismiss();
//            Log.i("tag","弹出框已经被销毁");
//            loadingDialog = null;
//        }
//    }

    //统一的toast短提示（0.5s）
    public static void toastAlert(Context context,String alert){
        Toast.makeText(context,alert, Toast.LENGTH_SHORT).show();
    }
    //统一的toast长提示（1.0s)
    public static void toastLongAlert(Context context,String alert){
        Toast.makeText(context,alert, Toast.LENGTH_LONG).show();
    }



}
