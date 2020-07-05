package com.cmt.base.utils;

import java.util.regex.Pattern;

/**
 * Date: 2019/12/28
 * Time: 14:26
 * author: cmt
 * desc: 正则表达式
 */
public class XRegexUtils {
    /**
     * 手机号码中间四位替换为*号
     */
    public static String phoneNoHide(String phoneNum){
        return phoneNum.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }

    //保留银行卡的后三位，其他星号
    public static String cradIdHide(String cardNum){
        return cardNum.replaceAll("\\d{15}(\\d{3})","**** **** **** **** $1");
    }

    /**
     * 检查昵称是否合格（字母、数字、下划线）
     * @param username
     * @return
     */
    public static boolean checkUsername(String username){
        Pattern pattern = Pattern.compile("^\\w+$");
        return pattern.matcher(username).find();
    }

    /**
     * 检查昵称是否合格（字母、数字、下划线、中文）
     * @param nickname
     * @return
     */
    public static boolean checkNickName(String nickname){
        Pattern pattern = Pattern.compile("\\w[\\u4E00-\\u9FA5]+");
        return pattern.matcher(nickname).find();
    }
}
