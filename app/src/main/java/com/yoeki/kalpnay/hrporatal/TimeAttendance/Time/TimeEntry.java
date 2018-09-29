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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeEntry extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    AppCompatButton timeEnter_home,time_from,time_to,HolidayFrom_date,time_entrySubmit;
    Edittextclass from_time_edittext,to_time_edittext,Stat_fromdate,wrked_time;
    private int mHour, mMinute, whichtime=0, mYear, mMonth, mDay;
    String Com_fromTime, Com_toTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_entry);

        timeEnter_home = (AppCompatButton)findViewById(R.id.timeEnter_home);
        HolidayFrom_date = (AppCompatButton)findViewById(R.id.HolidayFrom_date);
        time_from = (AppCompatButton)findViewById(R.id.time_from);
        time_to = (AppCompatButton)findViewById(R.id.time_to);
        time_entrySubmit = (AppCompatButton)findViewById(R.id.time_entrySubmit);
        from_time_edittext = (Edittextclass)findViewById(R.id.from_time_edittext);
        to_time_edittext = (Edittextclass)findViewById(R.id.to_time_edittext);
        Stat_fromdate = (Edittextclass)findViewById(R.id.Stat_fromdate);
        wrked_time = (Edittextclass)findViewById(R.id.wrked_time);

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

        time_entrySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverCode();
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
                            to_time_edittext.setText(String.format("%02d:%02d", hourOfDay, minute));
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                                Date date1 = null, date2 = null;
                                try {
                                    date1 = format.parse(to_time_edittext.getText().toString());
                                    date2 = format.parse(from_time_edittext.getText().toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                long mills = date1.getTime() - date2.getTime();
                                int hours = (int) (mills / (1000 * 60 * 60));
                                int mins = (int) ((mills / (1000 * 60)) % 60);
                                wrked_time.setText(String.format("%02d:%02d", hours, mins));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            whichtime=0;
                        }else{
                            Com_fromTime = hourOfDay + ":" + minute;
                            from_time_edittext.setText(String.format("%02d:%02d", hourOfDay, minute));
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                                Date date1 = null, date2 = null;
                                try {
                                    date1 = format.parse(to_time_edittext.getText().toString());
                                    date2 = format.parse(from_time_edittext.getText().toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                long mills = date1.getTime() - date2.getTime();
                                int hours = (int) (mills / (1000 * 60 * 60));
                                int mins = (int) ((mills / (1000 * 60)) % 60);
                                wrked_time.setText(String.format("%02d:%02d", hours, mins));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            whichtime=0;
                        }
                    }
                }, mHour, mMinute, true);
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

    public void serverCode(){
//        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
//        UserSend_Data user = new UserSend_Data("1");
//        Call<User> call2 = apiInterface.idUser(user);
//        call2.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                User user1 = response.body();
//                try {
//                    String status = user1.status;
//                    String mess = user1.message;
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//                setViewPager(user1);
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
    }
}
