package com.yoeki.kalpnay.hrporatal.TimeAttendance.Time;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;
import com.yoeki.kalpnay.hrporatal.setting.Edittextclass;

import java.util.Calendar;

public class TimeEntry extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
//    private com.applandeo.materialcalendarview.CalendarView mCalendarView;
    AppCompatButton timeEnter_home,time_from,time_to,HolidayFrom_date;
    Edittextclass from_time_edittext,to_time_edittext,Stat_fromdate;
    private int mHour, mMinute, whichtime=0, mYear, mMonth, mDay;
    String Com_fromTime, Com_toTime, Meridiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_entry);

//        mCalendarView = (com.applandeo.materialcalendarview.CalendarView) findViewById(R.id.calendarView);
        timeEnter_home = (AppCompatButton)findViewById(R.id.timeEnter_home);
        HolidayFrom_date = (AppCompatButton)findViewById(R.id.HolidayFrom_date);
        time_from = (AppCompatButton)findViewById(R.id.time_from);
        time_to = (AppCompatButton)findViewById(R.id.time_to);
        from_time_edittext = (Edittextclass)findViewById(R.id.from_time_edittext);
        to_time_edittext = (Edittextclass)findViewById(R.id.to_time_edittext);
        Stat_fromdate = (Edittextclass)findViewById(R.id.Stat_fromdate);

//        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
//            @Override
//            public void onDayClick(EventDay eventDay) {
////                Calendar clickedDayCalendar = eventDay.getCalendar();
//                Calendar selectedDate = mCalendarView.getFirstSelectedDate();
//            }
//        });

        HolidayFrom_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
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
                tiemPicker();
            }
        });

        time_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiemPicker();
                whichtime =1;
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

    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        if (whichtime == 1) {
                            Com_toTime = hourOfDay + ":" + minute;
                            to_time_edittext.setText(Com_toTime);
                            whichtime=0;
                        }else{
                            Com_fromTime = hourOfDay + ":" + minute;
                            from_time_edittext.setText(Com_fromTime);
                            whichtime=0;
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void datePicker(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date_time = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                        //*************Call Time Picker Here ********************
                        Stat_fromdate.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
