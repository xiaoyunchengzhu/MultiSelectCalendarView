package com.xiaoyunchengzhu.multiselectcalendarview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaoyunchengzhu.multiselectcalendarview.R;
import com.xiaoyunchengzhu.multiselectcalendarview.adapter.CalendarGridViewAdapter;
import com.xiaoyunchengzhu.multiselectcalendarview.bean.DateItem;
import com.xiaoyunchengzhu.multiselectcalendarview.util.ConfigUtils;
import com.xiaoyunchengzhu.multiselectcalendarview.util.inject.Inject;
import com.xiaoyunchengzhu.multiselectcalendarview.util.inject.ViewInject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * Created by zhangshiyu on 2016/8/25.
 */
public class CustomCalendarView extends LinearLayout {



    @Inject(R.id.calendarview_imgbutton_left)
    private ImageButton left;
    @Inject(R.id.calendarview_imgbutton_right)
    private ImageButton right;
    @Inject(R.id.calendarviewtext_month)
    private TextView moth;
    @Inject(R.id.calendarview_grid_gridview)
    private GridView gridView;
    private CalendarGridViewAdapter adapter;
    private int currentYear,currentMonth;
    private  List<String> selectdateList=new ArrayList<>();
    private  List<Date> currentWeekList=new ArrayList<>();
    private  List<Date> currentUnWeekList=new ArrayList<>();
    private int itembackground;
    private AttributeSet attrs;
    public CustomCalendarView(Context context) {
        super(context);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.custom_calendarview,null);
        ViewInject.inject(this,view);
        addView(view);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        inite(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));
    }

    public CustomCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs=attrs;
        View view= LayoutInflater.from(getContext()).inflate(R.layout.custom_calendarview,null);
        ViewInject.inject(this,view);
        addView(view);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        inite(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));

    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs=attrs;
        View view= LayoutInflater.from(getContext()).inflate(R.layout.custom_calendarview,null);
        ViewInject.inject(this,view);
        addView(view);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        inite(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));
    }



    private void inite(int year, int month){
        getAttrs(getContext(),attrs);
        currentYear=year;
        currentMonth=month;
        moth.setText(currentYear+"年 "+(currentMonth+1)+"月");
        List<DateItem> dateItems=new ArrayList<>();

        int firstPostion=getFirstDayWeek(currentYear,currentMonth);
        int monthDayNum=getMonthDays(currentYear,currentMonth);

        firstPostion--;
        for (int  i=1;i<=(monthDayNum+firstPostion);i++){

            DateItem dateItem=new DateItem();
            dateItem.setYear(year);
            dateItem.setMonth(month);
            if (i>firstPostion){

                int dayofmonth=i-firstPostion;
                dateItem.setDateOfMonth(dayofmonth);

                if (selectdateList!=null&&selectdateList.size()>0) {
                    for (String date : selectdateList) {

                        Calendar calendar=Calendar.getInstance();
                        calendar.setTime(ConfigUtils.simpleDate(date));
                        if (calendar.get(Calendar.YEAR)==year&&calendar.get(Calendar.MONTH)==month&&calendar.get(Calendar.DAY_OF_MONTH)==dayofmonth){
                            dateItem.setIsselect(true);
                        }
                    }
                }

            }
            dateItems.add(dateItem);
        }

        if (adapter==null) {
            adapter = new CalendarGridViewAdapter(this,getContext(), dateItems,itembackground);
            gridView.setAdapter(adapter);
        }else {
            if (dateItems!=null)
                adapter.setDate(dateItems);
        }
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setLeftOnclick();
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightOnclick();
            }
        });

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onitemClicklistnear){
        gridView.setOnItemClickListener(onitemClicklistnear);
    }
    public void swichUnWeekTheMonth(boolean isselect){
        for (int i=0;i<gridView.getChildCount();i++) {
            DateItem dateItem = (DateItem) adapter.getItem(i);

            if (dateItem.getDateOfMonth()!=0&&(i%7!=6&&i%7!=0)) {
                if (isselect) {
                    if (!dateItem.isselect()) {
                        dateItem.setIsselect(isselect);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(currentYear, currentMonth, dateItem.getDateOfMonth());
                        TextView textView = (TextView) gridView.getChildAt(i);
                        textView.setBackgroundColor(getResources().getColor(R.color.tet_blue));

                        addSelectDate(ConfigUtils.simpleDate(calendar.getTime()));
                    }
                } else {
                    if (dateItem.isselect()) {
                        dateItem.setIsselect(!dateItem.isselect());
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(currentYear, currentMonth, dateItem.getDateOfMonth());
                        TextView textView = (TextView) gridView.getChildAt(i);
                        textView.setBackgroundColor(getResources().getColor(R.color.base_bg));
                        removeSelect(ConfigUtils.simpleDate(calendar.getTime()));
                    }
                }
            }
        }
    }
    public void swichWeekendTheMonth(boolean isselect){
        for (int i=0;i<gridView.getChildCount();i++) {
            DateItem dateItem = (DateItem) adapter.getItem(i);

            if (dateItem.getDateOfMonth()!=0&&(i%7==6||i%7==0)) {
                if (isselect) {
                    if (!dateItem.isselect()) {
                        dateItem.setIsselect(isselect);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(currentYear, currentMonth, dateItem.getDateOfMonth());
                        TextView textView = (TextView) gridView.getChildAt(i);
                        textView.setBackgroundColor(getResources().getColor(R.color.tet_blue));

                        addSelectDate(ConfigUtils.simpleDate(calendar.getTime()));
                    }
                } else {
                    if (dateItem.isselect()) {
                        dateItem.setIsselect(!dateItem.isselect());
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(currentYear, currentMonth, dateItem.getDateOfMonth());
                        TextView textView = (TextView) gridView.getChildAt(i);
                        textView.setBackgroundColor(getResources().getColor(R.color.base_bg));

                        removeSelect(ConfigUtils.simpleDate(calendar.getTime()));
                    }
                }
            }
        }
    }



    public void setSelect(List<String> selectdateList){
        this.selectdateList=selectdateList;
        inite(currentYear,currentMonth);
        invalidate();
    }
    public  void addSelectDate(String date){
        selectdateList.add(date);
        invalidate();
    }
    public  void removeSelect(String date){
        selectdateList.remove(date);


    }
    public List<Date> getWeekOfMonthList(){
        return currentWeekList;
    }
    public List<Date> getUnWeekMonthList(){
        return currentUnWeekList;
    }

    public List<String> getSelectdateList(){
        return selectdateList;
    }
    private void setLeftOnclick(){

        int cutMonth=(currentMonth-1);
        if (cutMonth>=0) {
            inite(currentYear,cutMonth);
        }else {
            int cutYear=currentYear-1;
            inite(cutYear--,11);
        }
        invalidate();
    }
    private void setRightOnclick(){
        int cutMonth=(currentMonth+1);
        if (cutMonth<=11) {
            inite(currentYear,cutMonth);
        }else {
            int cutYear=currentYear+1;


            inite(cutYear,0);
        }
        invalidate();
    }
    public void clearCurrentMonthSelect(){
        for (int i=0;i<gridView.getChildCount();i++){
            DateItem dateItem= (DateItem) adapter.getItem(i);
            if (dateItem.isselect()) {
                dateItem.setIsselect(!dateItem.isselect());

                Calendar calendar=Calendar.getInstance();
                calendar.set(currentYear, currentMonth, dateItem.getDateOfMonth());
                TextView textView = (TextView) gridView.getChildAt(i);
                textView.setBackgroundColor(getResources().getColor(R.color.base_bg));

                removeSelect(ConfigUtils.simpleDate(calendar.getTime()));
            }
        }
    }
    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.calendarview);
        itembackground=ta.getResourceId(R.styleable.calendarview_item_select_backgroud,-1);
        ta.recycle();
    }


    /**
     * 通过年份和月份 得到当月的日子
     */
    private  int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
                    return 29;
                }else{
                    return 28;
                }
            default:
                return  -1;
        }
    }

    /**
     * 返回当前月份1号位于周几
     * @param year
     * 		年份
     * @param month
     * 		月份，传入系统获取的，不需要正常的
     * @return
     * 	日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    private  int getFirstDayWeek(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
