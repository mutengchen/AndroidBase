package com.cmt.base.format;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by cmt on 2019/12/6
 */
public class StringUtils {
    public static final String DEFAULT_QUERY_REGEX = "-";
    public static String isContainsEnglish = ".*[a-zA-z\\*-].*";
    /**
     * 检查网段格式是否正确
     * @param ip xxx.xxx.xxx.xxx/xx
     * @return
     */
    public static boolean checkIpReg(String ip){
        /**
         * 1\d{2}的意思就是100~199之间的任意一个数字
         * 2[0-4]\d的意思是200~249之间的任意一个数字
         * 25[0-5]的意思是250~255之间的任意一个数字
         * [1-9]\d的意思是10~99之间的任意一个数字
         * [1-9])的意思是1~9之间的任意一个数字
         */
        String ip_format = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)"+"/(1[0-9]|[0-9]|2[0-9]|3[0-2])$";
        //设置匹配器
        Pattern p = Pattern.compile(ip_format);
        Matcher m = p.matcher(ip);
        //返回匹配结果
        boolean res = m.matches();
        return res;
    }

    /**
     * 过滤掉特殊的字符串
     * @param orgStr
     * @return
     */
    public static String replaceSpecStr(String orgStr){
        if (null!=orgStr&&!"".equals(orgStr.trim())) {
            String regEx="[·！@￥$%^……&*（）【】\\|、\\\\；《。》、？]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(orgStr);
            return m.replaceAll("");
        }
        return null;
    }
    //处理千分位字符串，省略小数点后的数字
    public static String dealDataFormat(String a){
        double temp = 0;
        if(a==null){
            return "-";
        }else{
            if((!a.equals("-")&&!a.matches(isContainsEnglish))||specialSymbols(a)){
                temp = Double.parseDouble(a);
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                return numberFormat.format((int)temp);
            }
        }
        return a;
    }
    //处理千分位字符串，保留小数点后两位
    public static String changDataFormatDouble(String a){
        double temp = 0;
        if(a==null){
            return "-";
        }else{
            if((!a.equals("-")&&!a.matches(isContainsEnglish))||specialSymbols(a)){
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                numberFormat.setMaximumFractionDigits(2);
                temp = Double.parseDouble(a);
                return numberFormat.format(temp);
            }
        }
        return a;
    }
    //首字母大写
    public static String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static boolean specialSymbols(String value) {
        if(value.length()==1){
            return false;
        }
        Pattern pattern = Pattern.compile(DEFAULT_QUERY_REGEX);
        Matcher matcher = pattern.matcher(value);

        char[] specialSymbols = DEFAULT_QUERY_REGEX.toCharArray();

        boolean isStartWithSpecialSymbol = false; // 是否以特殊字符开头
        for (int i = 0; i < specialSymbols.length; i++) {
            char c = specialSymbols[i];
            if (value.indexOf(c) == 0) {
                isStartWithSpecialSymbol = true;
                break;
            }
        }

        return matcher.find() && isStartWithSpecialSymbol;
    }
}
