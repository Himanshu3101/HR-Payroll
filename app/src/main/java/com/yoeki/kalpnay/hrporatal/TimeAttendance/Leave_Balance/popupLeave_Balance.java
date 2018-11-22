package com.yoeki.kalpnay.hrporatal.TimeAttendance.Leave_Balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IACE on 12-Nov-18.
 */

public class popupLeave_Balance extends Activity {
    Textclass leaveType;
    RecyclerView balance_leaveListRecycler;
    List<String>details_leaveLists;
    String typeLeave;
    AppCompatButton kk_leaveDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_leave_details);

        typeLeave = getIntent().getStringExtra("Leave Type");
        leaveType = (Textclass) findViewById(R.id.leaveType);
        balance_leaveListRecycler = (RecyclerView)findViewById(R.id.balance_leaveListRecycler);
        kk_leaveDetails = (AppCompatButton)findViewById(R.id.kk_leaveDetails);
        leaveType.setText(typeLeave);

        kk_leaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Leave_Balance.class);
                startActivity(intent);
                finish();
            }
        });

        leave_recycler_benefit();
    }

    public void leave_recycler_benefit(){

        details_leaveLists = new ArrayList<>();
        details_leaveLists.add("01/11/2018,03/11/2018,"+typeLeave);
        details_leaveLists.add("05/11/2018,07/11/2018,"+typeLeave+" for Critical");
//        details_leaveLists.add("01/11/2018,02/11/2018,"+typeLeave);
//        details_leaveLists.add("01/11/2018,02/11/2018,"+typeLeave);
//        details_leaveLists.add("01/11/2018,02/11/2018,"+typeLeave);
//        details_leaveLists.add("01/11/2018,02/11/2018,"+typeLeave);
//        details_leaveLists.add("01/11/2018,02/11/2018,"+typeLeave);
//        details_leaveLists.add("01/11/2018,02/11/2018,"+typeLeave);
//        details_leaveLists.add("01/11/2018,02/11/2018,"+typeLeave);

        balance_leaveListRecycler.setLayoutManager(new LinearLayoutManager(this));
        Full_detail_leave_balance full_detail_leave_balance = new Full_detail_leave_balance(this,details_leaveLists);
        balance_leaveListRecycler.setAdapter(full_detail_leave_balance);
    }
}
