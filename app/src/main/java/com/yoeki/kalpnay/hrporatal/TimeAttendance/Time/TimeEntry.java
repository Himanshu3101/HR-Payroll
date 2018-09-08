package com.yoeki.kalpnay.hrporatal.TimeAttendance.Time;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;
import com.yoeki.kalpnay.hrporatal.setting.Edittextclass;

import java.util.Calendar;

public class TimeEntry extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    private com.applandeo.materialcalendarview.CalendarView mCalendarView;
    AppCompatButton timeEnter_home,time_from,time_to;
    Edittextclass from_time_edittext,to_time_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_entry);

        mCalendarView = (com.applandeo.materialcalendarview.CalendarView) findViewById(R.id.calendarView);
        timeEnter_home = (AppCompatButton)findViewById(R.id.timeEnter_home);
        time_from = (AppCompatButton)findViewById(R.id.time_from);
        time_to = (AppCompatButton)findViewById(R.id.time_to);
        from_time_edittext = (Edittextclass)findViewById(R.id.from_time_edittext);
        to_time_edittext = (Edittextclass)findViewById(R.id.to_time_edittext);

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
//                Calendar clickedDayCalendar = eventDay.getCalendar();
                Calendar selectedDate = mCalendarView.getFirstSelectedDate();
            }
        });

        timeEnter_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TimeAttendance_Menu.class);
                startActivity(intent);
                finish();
            }
        });

        time_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        time_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),TimeAttendance_Menu.class);
        startActivity(intent);
        finish();
    }
}
