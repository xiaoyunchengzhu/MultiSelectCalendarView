package com.xiaoyunchengzhu.multiselectcalendarview.util;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangshiyu on 2016/6/1.
 */
public class ConfigUtils {

    public static int getScreenWidth(Activity activity) {
        WindowManager wm = (WindowManager)activity
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();

        return width;
    }
    public static String simpleDate(Date date){
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    public static Date simpleDate(String data){
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {

            return  format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
