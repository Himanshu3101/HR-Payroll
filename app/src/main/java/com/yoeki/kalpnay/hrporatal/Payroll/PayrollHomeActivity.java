package com.yoeki.kalpnay.hrporatal.Payroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yoeki.kalpnay.hrporatal.R;

public class PayrollHomeActivity extends AppCompatActivity implements View.OnClickListener {
     private ImageView img_payrollback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_salayviewslihp);
        initialize();

        img_payrollback.setOnClickListener(this);
    }

    public  void initialize(){
        img_payrollback=findViewById(R.id.img_payrollback);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_payrollback:
                finish();
                break;

        }
    }
}
