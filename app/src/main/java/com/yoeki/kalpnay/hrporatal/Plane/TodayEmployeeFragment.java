package com.yoeki.kalpnay.hrporatal.Plane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionLayout;
import com.yoeki.kalpnay.hrporatal.Notification.NotificationModel;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class TodayEmployeeFragment extends Fragment {

    private RecyclerView ryc_planemployeetoday;
    ArrayList<NotificationModel> arraycircularlist;
    FloatingActionLayout fab_employeetoday;

    public static TodayEmployeeFragment newInstance() {
        TodayEmployeeFragment fragment = new TodayEmployeeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_todayemployee, container, false);
        ryc_planemployeetoday=view.findViewById(R.id.ryc_planemployeetoday);

        fab_employeetoday =view.findViewById(R.id.fab_employeetoday);
        fab_employeetoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent=new Intent(getActivity(),EmployeeRequestformActivity.class);
                getActivity().startActivity(intent);

            }
        });

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

        ryc_planemployeetoday.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        ryc_planemployeetoday.setItemAnimator(new DefaultItemAnimator());

        PlanAdapter adapter=new PlanAdapter(getActivity(),arraycircularlist);
        ryc_planemployeetoday.setAdapter(adapter);

        return view;

    }
}
