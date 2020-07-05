package com.cmt.base.format;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by cmt on 2019/12/6
 */
public class DateUtils {

    //计算岁数
    public static int calAge(String birthDay){
        Log.e("birthDay",birthDay);
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date date = null;
        birthDay = birthDay.replaceAll("/","-");

        try {
            date = sdf.parse(birthDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();

        int  yearNow = cal.get(Calendar.YEAR);
        int  monthNow = cal.get(Calendar.MONTH);
        int  dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(date);

        int  yearBirth = cal.get(Calendar.YEAR);
        int  monthBirth = cal.get(Calendar.MONTH);
        int  dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int  age = yearNow - yearBirth;

        if (monthNow <= monthBirth)
        {
            if (monthNow == monthBirth)
            {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            }
            else
            {
                age--;
            }
        }
        return  age;
    }
}
