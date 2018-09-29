package com.yoeki.kalpnay.hrporatal.Plane;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.Notification.NotificationModel;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class PlaneActivity extends AppCompatActivity implements View.OnClickListener {

  //  private ImageView img_backplane;
    private RecyclerView ryc_plan;
    ArrayList<NotificationModel> arraycircularlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane);
        initialize();



        arraycircularlist=new ArrayList<>();

        NotificationModel data=new NotificationModel();
        data.setNotificationName("Mohit Kumar");
        data.setNotificationMsg("South Delhi");
        arraycircularlist.add(data);

        NotificationModel data1=new NotificationModel();
        data1.setNotificationName("Himanshu Kumar");
        data1.setNotificationMsg("E-block,sec-63 Noida");

        arraycircularlist.add(data1);

        NotificationModel data2=new NotificationModel();
        data2.setNotificationName("Mohit Kumar");
        data2.setNotificationMsg("sec-15, Noida");
        arraycircularlist.add(data2);

        NotificationModel data3=new NotificationModel();
        data3.setNotificationName("Fateh");
        data3.setNotificationMsg("Vijay Nagar,Ghaziabad");
        arraycircularlist.add(data3);

        NotificationModel data4=new NotificationModel();
        data4.setNotificationName("Himanshu");
        data4.setNotificationMsg("NandGram, Ghaziabad");
        arraycircularlist.add(data4);

        NotificationModel dat5=new NotificationModel();
        dat5.setNotificationName("Mohit Kumar");
        dat5.setNotificationMsg("New Ashok Nagar,Delhi");
        arraycircularlist.add(dat5);

        NotificationModel data6=new NotificationModel();
        data6.setNotificationName("Mohit Kumar");
        data6.setNotificationMsg("sec-15, Noida");
        arraycircularlist.add(data6);

        NotificationModel data7=new NotificationModel();
        data7.setNotificationName("Fateh");
        data7.setNotificationMsg("Vijay Nagar,Ghaziabad");
        arraycircularlist.add(data7);

        NotificationModel data8=new NotificationModel();
        data8.setNotificationName("Himanshu");
        data8.setNotificationMsg("NandGram, Ghaziabad");
        arraycircularlist.add(data8);

        ryc_plan.setLayoutManager(new LinearLayoutManager(PlaneActivity.this, LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        ryc_plan.setItemAnimator(new DefaultItemAnimator());

        PlanAdapter adapter=new PlanAdapter(PlaneActivity.this,arraycircularlist);
        ryc_plan.setAdapter(adapter);

       // img_backplane.setOnClickListener(this);

    }
    public  void initialize(){

     //   img_backplane=findViewById(R.id.img_backplane);
        ryc_plan=findViewById(R.id.ryc_plan);

    }
    @Override
    public void onClick(View v) {
       /* switch (v.getId()){
            case R.id.img_backplane:
                finish();
                break;
        }*/
    }
}
