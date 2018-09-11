package com.yoeki.kalpnay.hrporatal.Request;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yoeki.kalpnay.hrporatal.R;

public class RequestMenu extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_backrequest;
    private LinearLayout ly_leaverequest,ly_claimreq,ly_trainningrequest,ly_greveance,ly_shiftchange,ly_advance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_menu);

        initialize();

        img_backrequest.setOnClickListener(this);
        ly_leaverequest.setOnClickListener(this);
        ly_claimreq.setOnClickListener(this);
        ly_trainningrequest.setOnClickListener(this);
        ly_greveance.setOnClickListener(this);
        ly_shiftchange.setOnClickListener(this);
        ly_advance.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_backrequest:
                finish();
                break;

            case R.id.ly_leaverequest:
                Intent intent=new Intent(RequestMenu.this,LeaveRequest.class);
                startActivity(intent);
                break;
            case R.id.ly_claimreq:
                 Intent intentclaim=new Intent(RequestMenu.this,ClaimActivity.class);
                 startActivity(intentclaim);
                 break;
            case R.id.ly_trainningrequest:
                Intent intenttraining=new Intent(RequestMenu.this,TrainingActivity.class);
                startActivity(intenttraining);
                break;
            case R.id.ly_greveance:
                Intent intentgreveance=new Intent(RequestMenu.this,GreveanceActivity.class);
                startActivity(intentgreveance);
                break;
            case R.id.ly_shiftchange:
                Intent intentshiftchange=new Intent(RequestMenu.this,ShiftChange.class);
                startActivity(intentshiftchange);
                break;
            case R.id.ly_advance:
                Intent intentadvance=new Intent(RequestMenu.this,AdvanceActivity.class);
                startActivity(intentadvance);
                break;
        }
    }

    public  void initialize(){

        ly_advance=findViewById(R.id.ly_advance);
        img_backrequest=findViewById(R.id.img_backrequest);
        ly_leaverequest=findViewById(R.id.ly_leaverequest);
        ly_claimreq=findViewById(R.id.ly_claimreq);
        ly_trainningrequest=findViewById(R.id.ly_trainningrequest);
        ly_greveance=findViewById(R.id.ly_greveance);
        ly_shiftchange=findViewById(R.id.ly_shiftchange);

    }
}
