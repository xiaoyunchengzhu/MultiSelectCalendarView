package com.xiaoyunchengzhu.multiselectcalendarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xiaoyunchengzhu.multiselectcalendarview.customview.CustomCalendarView;
import com.xiaoyunchengzhu.multiselectcalendarview.util.inject.Inject;
import com.xiaoyunchengzhu.multiselectcalendarview.util.inject.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject(R.id.calendarview)
    private CustomCalendarView calendarView;
    @Inject(R.id.showselectbutton)
    private Button show;
    @Inject(R.id.showselect)
    private TextView showtext;
    private List<String> initeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInject.inject(this);
        initeData=new ArrayList<>();
        for (int i=0;i<4;i++){
            String data="2016-08-"+(27+i);
            initeData.add(data);
        }
        for (int i=0;i<2;i++){
            String data="2016-09-"+(27+i);
            initeData.add(data);
        }
        calendarView.setSelect(initeData);
        show.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        showtext.setText(new Gson().toJson(calendarView.getSelectdateList()));
    }
}
