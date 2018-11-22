package com.yoeki.kalpnay.hrporatal.Notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

public class EventDetails extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_detailsback;
    String msg,eventdate,name;
    TextView  tv_msgtype,tv_eventdetaildate,tv_eventdetailmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        initialize();

        msg=getIntent().getStringExtra("msg");
        eventdate=getIntent().getStringExtra("date");
        name=getIntent().getStringExtra("name");

        img_detailsback.setOnClickListener(this);

        tv_msgtype.setText(name);
        tv_eventdetaildate.setText(eventdate);
        tv_eventdetailmsg.setText(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.img_detailsback:
                finish();
                break;
        }

    }

    public void initialize(){

        img_detailsback=findViewById(R.id.img_detailsback);
        tv_msgtype=findViewById(R.id.tv_msgtype);
        tv_eventdetaildate=findViewById(R.id.tv_eventdetaildate);
        tv_eventdetailmsg=findViewById(R.id.tv_eventdetailmsg);

    }
}
