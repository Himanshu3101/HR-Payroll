package com.yoeki.kalpnay.hrporatal.TimeAttendance.Holidays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;

import java.util.ArrayList;

/**
 * Created by IACE on 03-Sep-18.
 */

public class Holiday_activity extends AppCompatActivity {

    AppCompatButton /*HolidayTo_date,HolidayFrom_date,*/ holid_home/*, holidayDateSubmit*/;
//    Edittextclass Stat_fromdate,Stat_todate;
    String Com_fromDate, Com_toDate;
    private int mYear, mMonth, mDay;
    int whichdate=0;
    Boolean validation;
    RecyclerView EventView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday);

//        HolidayTo_date = (AppCompatButton)findViewById(R.id.HolidayTo_date);
//        HolidayFrom_date = (AppCompatButton)findViewById(R.id.HolidayFrom_date);
        holid_home = (AppCompatButton)findViewById(R.id.holid_home);
//        holidayDateSubmit = (AppCompatButton)findViewById(R.id.holidayDateSubmit);
//        Stat_fromdate = (Edittextclass)findViewById(R.id.Stat_fromdate);
//        Stat_todate = (Edittextclass)findViewById(R.id.Stat_todate);
        EventView = (RecyclerView) findViewById(R.id.eventsList);

//        HolidayFrom_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                datePicker();
//            }
//        });
//
//        HolidayTo_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                whichdate=1;
//                datePicker();
//            }
//        });

        holid_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TimeAttendance_Menu.class);
                startActivity(intent);
                finish();
            }
        });
        recyclerData();

//        holidayDateSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validations();
//                if (validation == true) {
//                    Date fdate;
//                    Date tdate;
//
//                    try {
//                        fdate = new SimpleDateFormat("dd/MM/yyyy").parse(Com_fromDate);
//                        tdate = new SimpleDateFormat("dd/MM/yyyy").parse(Com_toDate);
//
//                        if(fdate.after(tdate)){
//                            Toast.makeText(Holiday_activity.this, "From Date not less then by To date", Toast.LENGTH_SHORT).show();
//                        }else if(fdate.before(tdate)){
//                            recyclerData();
//                        }else if(fdate.equals(tdate)){
//                            recyclerData();
//                        }
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),TimeAttendance_Menu.class);
        startActivity(intent);
        finish();
    }

    public void recyclerData(){
        ArrayList<String> EventList = new ArrayList<>();
        EventList.add("15~Aug~Independence Day");
        EventList.add("5~Sep~Teacher's Day");
        EventList.add("2~Oct~Mahatma Gandhi Jayanti");
        EventList.add("18~Oct~Maha Navami");
        EventList.add("19~Oct~Dussehra");

        EventView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        HolidayRecyclerViewAdapter hadapter = new HolidayRecyclerViewAdapter( getApplicationContext() ,EventList);
        EventView.setAdapter(hadapter);
    }

//    private void datePicker(){
//        // Get Current Date
//        final Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        String date_time = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
//                        //*************Call Time Picker Here ********************
//                        if (whichdate == 1) {
//                            Com_toDate = date_time;
//                            Stat_todate.setText(date_time);
//                            whichdate=0;
//                        }else{
//                            Com_fromDate = date_time;
//                            Stat_fromdate.setText(date_time);
//                            whichdate=0;
//                        }
//                    }
//                }, mYear, mMonth, mDay);
//        datePickerDialog.show();
//    }
//
//    public boolean validations() {
//        validation = true;
//
//        if (Stat_fromdate.getText().toString().equals("") && Stat_todate.getText().toString().equals("")) {
//            validation = false;
//            Stat_fromdate.setError("Enter From Date");
//            Stat_todate.setError("Enter To Date");
//
//        }else if (Stat_fromdate.getText().toString().equals("")) {
//            validation = false;
//            Stat_fromdate.setError("Enter From Date");
//        }else if (Stat_todate.getText().toString().equals("")) {
//            validation = false;
//            Stat_todate.setError("Enter To Date");
//        }
//        return validation;
//    }


}
