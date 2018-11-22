package com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;
import com.yoeki.kalpnay.hrporatal.setting.Edittextclass;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by IACE on 05-Sep-18.
 */

public class Requests extends AppCompatActivity {
    RecyclerView requests_list;
    AppCompatButton req_bck,request_filter;
    int whichdate=0;
    String Com_fromDate, Com_toDate;
    private int mYear, mMonth, mDay;
    Edittextclass req_filter_fromdate,req_filter_todate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests_all);

        requests_list = (RecyclerView) findViewById(R.id.requests_list);
        req_bck = (AppCompatButton)findViewById(R.id.req_back);
        request_filter = (AppCompatButton)findViewById(R.id.request_filter);

        req_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeAttendance_Menu.class);
                startActivity(intent);
                finish();
            }
        });

        request_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             filterRequest();
            }
        });

        ArrayList<String> RequestList = new ArrayList<>();
        RequestList.add("15/Aug/18~Ashish Kumar~Sick Request");
        RequestList.add("25/Aug/18~Sachin Singh~Annual Request");
        RequestList.add("10/Sep/18~Rahul Sharma~Emergency Request");
        RequestList.add("15/Sep/18~Shahsank Tyagi~Medical Request");
        RequestList.add("15/Oct/18~Manoj Tiwari~Sick Request");
        RequestList.add("25/Nov/18~Rohit Yadav~Materanity Request");

        requests_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RequestRecyclerViewAdapter hadapter = new RequestRecyclerViewAdapter( getApplicationContext() ,RequestList);
        requests_list.setAdapter(hadapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), TimeAttendance_Menu.class);
        startActivity(intent);
        finish();
    }

    public void filterRequest(){
        AppCompatButton req_filterFrom_date,req_filterTo_date;
        Textclass saveFilter;
        Spinner srch_req_by_type;
        ArrayAdapter<String> reqt_lst_Name;

        final Dialog dialog = new Dialog(Requests.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.request_filter);
        dialog.setCanceledOnTouchOutside(true);
        req_filterFrom_date = dialog.findViewById(R.id.req_filterFrom_date);
        req_filterTo_date = dialog.findViewById(R.id.req_filterTo_date);
        req_filter_fromdate = dialog.findViewById(R.id.req_filter_fromdate);
        req_filter_todate = dialog.findViewById(R.id.req_filter_todate);
        srch_req_by_type = dialog.findViewById(R.id.srch_req_by_type);
        saveFilter = dialog.findViewById(R.id.saveFilter);

        req_filterFrom_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        req_filterTo_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichdate=1;
                datePicker();
            }
        });

        saveFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),Requests.class);
              startActivity(intent);
              finish();
            }
        });

        final ArrayList RequestListArray = new ArrayList<>();
        RequestListArray.add("Sick Leave");
        RequestListArray.add("Annual Leave");
        RequestListArray.add("Emergency Leave");
        RequestListArray.add("Casual Leave");
        RequestListArray.add("Medical Leave");
        RequestListArray.add("Paid Leave");
        RequestListArray.add("Un-Paid Leave");
        RequestListArray.add("Materanity Leave");

        reqt_lst_Name = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, RequestListArray);
        reqt_lst_Name.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        srch_req_by_type.setAdapter(reqt_lst_Name);
        reqt_lst_Name.insert("--Search by Type--", 0);

        srch_req_by_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String client_Selection = adapterView.getItemAtPosition(position).toString();
                String check_client_nme = "--Select Request Type--";

                if(!client_Selection.equals("--Search by Type--")){

                    for (int i = 0; i < RequestListArray.size(); i++) {

                        String listName = String.valueOf(RequestListArray.get(i));

//                        if (listName.equals(client_Selection)) {
//                            try{
////                                requesttype.setText(listName);
//                                Requestids = String.valueOf(db.getReqListID(listName));
//                                S=1;
//
//                            }catch(Exception e){
//                                e.printStackTrace();
//                            }
//                        }
                    }
//                    datafrrequest();
                }else{
//                    requesttype.setText("--Search by Type--");
//                    datafrrequest();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dialog.show();
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
                        if (whichdate == 1) {
                            Com_toDate = date_time;
                            req_filter_todate.setText(date_time);
                            whichdate=0;
                        }else{
                            Com_fromDate = date_time;
                            req_filter_fromdate.setText(date_time);
                            whichdate=0;
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}












