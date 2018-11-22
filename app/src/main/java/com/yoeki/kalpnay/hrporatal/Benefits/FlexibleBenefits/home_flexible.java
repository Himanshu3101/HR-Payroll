package com.yoeki.kalpnay.hrporatal.Benefits.FlexibleBenefits;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.Benefits.Home;
import com.yoeki.kalpnay.hrporatal.R;

/**
 * Created by IACE on 17-Sep-18.
 */

public class home_flexible extends AppCompatActivity {
    AppCompatButton flexibleBenefits_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_flexbile);
        flexibleBenefits_back = (AppCompatButton)findViewById(R.id.flexibleBenefits_back);
        flexibleBenefits_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }
}

