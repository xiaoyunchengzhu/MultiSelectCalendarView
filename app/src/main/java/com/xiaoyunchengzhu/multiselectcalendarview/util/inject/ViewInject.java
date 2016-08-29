package com.xiaoyunchengzhu.multiselectcalendarview.util.inject;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.Field;

/**
 * Created by zhangshiyu on 2016/6/2.
 */
public class ViewInject {

    public static void injectList(Activity activity){

        Class<?> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field:declaredFields)
        {

            if (field.isAnnotationPresent(Inject.class))
            {
                Inject annotation = field.getAnnotation(Inject.class);
                Object[] objects=null;
                try {
                    objects= (Object[]) field.get(activity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                for (int i=0;i<annotation.value().length;i++){
                    objects[i]=activity.findViewById(annotation.value()[i]);
                }
                field.setAccessible(true);
                try {
                    field.set(activity,objects);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static void inject(Activity activity)
    {
        int m=0,n=0;
        Class<?> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field:declaredFields)
        {

            if (field.isAnnotationPresent(Inject.class))
            {

                if(n>=m){
                    n=0;
                }
                Inject annotation = field.getAnnotation(Inject.class);
                m=annotation.value().length;
                field.setAccessible(true);
                try {
                    field.set(activity,activity.findViewById(annotation.value()[n]));
                    n++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void inject(Object object,View view){
        int m=0,n=0;
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field:declaredFields)
        {
            if (field.isAnnotationPresent(Inject.class))
            {
                if(n>=m){
                    n=0;
                }
                Inject annotation = field.getAnnotation(Inject.class);
                m=annotation.value().length;
                field.setAccessible(true);
                try {
                    field.set(object,view.findViewById(annotation.value()[n]));
                    n++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
