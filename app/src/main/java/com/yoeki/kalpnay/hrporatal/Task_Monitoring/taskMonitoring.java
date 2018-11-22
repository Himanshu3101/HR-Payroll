package com.yoeki.kalpnay.hrporatal.Task_Monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;
import com.yoeki.kalpnay.hrporatal.R;

/**
 * Created by IACE on 14-Sep-18.
 */

public class taskMonitoring extends AppCompatActivity {
    AppCompatButton benefits_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_monitoring_home);

        benefits_back = (AppCompatButton)findViewById(R.id.benefits_back);

        benefits_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
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
