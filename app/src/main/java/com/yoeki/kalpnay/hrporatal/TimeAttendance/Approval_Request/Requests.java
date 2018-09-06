package com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request;

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
 * Created by IACE on 05-Sep-18.
 */

public class Requests extends AppCompatActivity {
    RecyclerView requests_list;
    AppCompatButton req_bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests_all);

        requests_list = (RecyclerView) findViewById(R.id.requests_list);
        req_bck = (AppCompatButton)findViewById(R.id.req_back);

        req_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeAttendance_Menu.class);
                startActivity(intent);
                finish();
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
}
