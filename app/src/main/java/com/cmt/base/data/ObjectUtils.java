package com.cmt.base.data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/* 获取对象的属性的key-value
 * Created by cmt on 2019/6/3
 */
public class ObjectUtils {
    /**
     * 获取属性名数组
     * @param o
     * @return
     */
    //获取对象的属性名称
    public static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性名获取对应的属性值
     * @param fieldName
     * @param o
     * @return
     */
    //获取对象属性值
    public static  Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {

            return null;
        }
    }

    private static final String defaultStr = "-";
    private static final Date defaultDate = new Date();
    private static final BigDecimal defaultDecimal = new BigDecimal(0);
    private static final Timestamp defaultTimestamp=new Timestamp(new Date().getTime());
    //赋默认值
    public static void setDefaultValue(Object object) {
        try {
            Class clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
//            String primaryKey = EntityUtil.getPrimaryKey(currentSession(), object.getClass());
            for(int i=0;i<fields.length;i++){
                Field field = fields[i];
                String fieldName = field.getName();
                Class fieldClass=field.getType();
                field.setAccessible(true); //设置访问权限
                if(isFieldValueNull(fieldName,object)){
                    if (fieldClass == Integer.class ) {
                        field.set(object, defaultDecimal.intValue());
                    }else if (fieldClass == Long.class) {
                        field.set(object, defaultDecimal.longValue());
                    }else if (fieldClass == Float.class) {
                        field.set(object, defaultDecimal.doubleValue());
                    }else if (fieldClass == BigDecimal.class) {
                        field.set(object, defaultDecimal);
                    } else if (fieldClass == Date.class) {
                        field.set(object, defaultDate);
                    } else if (fieldClass == String.class){
                        field.set(object, defaultStr); // 设置值
                    } else if (fieldClass == Timestamp.class){
                        field.set(object, defaultTimestamp);
                    }
                }else if( isStringFieldValueNull(fieldName,object,fieldClass)){//MySQL，需要对对主键做特殊处理
                    field.set(object, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //判断字段是否为空
    public static boolean isFieldValueNull(String fieldName, Object object) throws ClassNotFoundException {
        boolean isNUll=false;
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = object.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(object, new Object[] {});
            if(value==null){
                isNUll=true;
            }
            return isNUll;
        } catch (Exception e) {
            return isNUll;
        }
    }

    //判断主键是否为空值
    private static boolean isStringFieldValueNull(String fieldName, Object object, Class fieldClass) throws ClassNotFoundException {
        boolean isNUll=false;
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = object.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(object, new Object[] {});
            if(value==null ){
                isNUll=true;
            }else{
                if (fieldClass == String.class && ((String)value).equals("")) {
                    isNUll=true;
                }
            }
            return isNUll;
        } catch (Exception e) {
            return isNUll;
        }
    }



}
