package com.yoeki.kalpnay.hrporatal.TimeAttendance.Leave_Balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IACE on 06-Sep-18.
 */

public class Leave_Balance extends AppCompatActivity {
    RecyclerView balance_leave;
    List<String> balance_lists;
    AppCompatButton balance_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_balance);

        balance_leave = (RecyclerView)findViewById(R.id.balance_List);
        balance_back = (AppCompatButton)findViewById(R.id.balance_back);
        balance_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TimeAttendance_Menu.class);
                startActivity(intent);
                finish();
            }
        });

        recycler_benefit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplication(), TimeAttendance_Menu.class);
        startActivity(intent);
        finish();
    }

    public interface ItemClickListener {
        void onItemClick(leave_balanceRecycler.ViewHolder view, int position);
    }

    public void recycler_benefit(){
        balance_lists = new ArrayList<>();
        balance_lists.add("5,Sick Leave");
        balance_lists.add("3,Annual Leave");
        balance_lists.add("8,Emergency Leave");
        balance_lists.add("4,Casual Leave");
        balance_lists.add("6,Medical Leave");
        balance_lists.add("2,Paid Leave");
        balance_lists.add("5,Un-Paid Leave");
        balance_lists.add("3,Materanity Leave");

        try {
            balance_leave.setLayoutManager(new LinearLayoutManager(this));
            leave_balanceRecycler leave_adapter = new leave_balanceRecycler(this,balance_lists);
            balance_leave.setAdapter(leave_adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
