package com.yoeki.kalpnay.hrporatal.TimeAttendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.Request.LeaveRequest;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request.ViewPager.RequestManagementTab;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Holidays.Holiday_activity;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Time.TimeEntry;

/**
 * Created by IACE on 03-Sep-18.
 */

public class TimeAttendance_Menu extends AppCompatActivity {
    CardView time_sheet,holiday,leave_request,leave_balance,approve_Request;
    SwitchCompat switchCompat;
    AppCompatButton img_backrequest;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_attendance_menu);

        time_sheet = (CardView)findViewById(R.id.time_sheet);
        holiday = (CardView)findViewById(R.id.holiday);
        leave_request = (CardView)findViewById(R.id.leave_request);
        leave_balance = (CardView)findViewById(R.id.leave_balance);
//        approve_Request = (CardView)findViewById(R.id.approve_Request);
        switchCompat=(SwitchCompat)findViewById(R.id.ShowRequest);
        img_backrequest = (AppCompatButton)findViewById(R.id.img_backrequest);

        img_backrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        time_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeAttendance_Menu.this,TimeEntry.class);
                startActivity(intent);
                finish();
            }
        });

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeAttendance_Menu.this,Holiday_activity.class);
                startActivity(intent);
                finish();
            }
        });

        leave_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeAttendance_Menu.this,LeaveRequest.class);
                intent.putExtra("whereCome","fromTime");
                startActivity(intent);
                finish();
            }
        });

        leave_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TimeAttendance_Menu.this,TimeEntry.class);
//                startActivity(intent);
//                finish();
            }
        });

//        approve_Request.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(TimeAttendance_Menu.this,TimeEntry.class);
////                startActivity(intent);
////                finish();
//            }
//        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String stat = String.valueOf(isChecked);
                if(stat.equals("true")){
                    Intent intent0=new Intent(getApplicationContext(),RequestManagementTab.class);
//                    Intent intent0=new Intent(getApplicationContext(),Requests.class);
                    startActivity(intent0);
                    finish();
                }else{
                    switchCompat.setChecked(false);
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}