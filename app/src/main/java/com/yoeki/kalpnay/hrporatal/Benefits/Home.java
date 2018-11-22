package com.yoeki.kalpnay.hrporatal.Benefits;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IACE on 13-Sep-18.
 */

public class Home extends AppCompatActivity {
    RecyclerView Benefits_List;
    List<String> benefit_lists;
    AppCompatButton benefits_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benefit_home);

        Benefits_List = (RecyclerView)findViewById(R.id.Benefits_List);
        benefits_back = (AppCompatButton) findViewById(R.id.benefits_back);

        benefits_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
       recycler_benefit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void recycler_benefit(){
        benefit_lists = new ArrayList<>();
        benefit_lists.add("Health Care");
        benefit_lists.add("Critical/Illness");
        benefit_lists.add("Personal Benefits");
        benefit_lists.add("Retirement Benefits");
        benefit_lists.add("Flexible Benefits");
        benefit_lists.add("Creative Benefits/Perks");
        benefit_lists.add("Office Ergonomics");

        try {
            Benefits_List.setLayoutManager(new LinearLayoutManager(this));
            benefit_homeRecycler radapter = new benefit_homeRecycler(this,benefit_lists);
                Benefits_List.setAdapter(radapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
