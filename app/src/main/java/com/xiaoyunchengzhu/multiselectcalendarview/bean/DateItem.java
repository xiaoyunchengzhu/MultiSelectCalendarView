package com.xiaoyunchengzhu.multiselectcalendarview.bean;

/**
 * Created by zhangshiyu on 2016/8/26.
 */
public class DateItem {

    private int dateOfMonth=0;
    private boolean isselect=false;
    private int year;
    private int month;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDateOfMonth() {
        return dateOfMonth;
    }

    public void setDateOfMonth(int dateOfMonth) {
        this.dateOfMonth = dateOfMonth;
    }

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }
}
