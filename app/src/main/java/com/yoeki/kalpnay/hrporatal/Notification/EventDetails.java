package com.yoeki.kalpnay.hrporatal.Notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yoeki.kalpnay.hrporatal.R;

public class EventDetails extends AppCompatActivity implements View.OnClickListener {

  private ImageView img_detailsback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

                  initialize();

        img_detailsback.setOnClickListener(this);

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

    }
}
