package com.yoeki.kalpnay.hrporatal.Notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class CircularFragment extends Fragment {

    private RecyclerView ryc_circular;
    ArrayList<NotificationModel> arraycircularlist;
    public static CircularFragment newInstance() {
        CircularFragment fragment = new CircularFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circular, container, false);

        ryc_circular=view.findViewById(R.id.ryc_circular);

        arraycircularlist=new ArrayList<>();

        NotificationModel data=new NotificationModel();
        data.setNotificationName("Mohit Kumar");
        data.setNotificationMsg("Today is Holiday");
        data.setNotificationDate("22-08-2018");
        arraycircularlist.add(data);

        NotificationModel data1=new NotificationModel();
        data1.setNotificationName("Himanshu Kumar");
        data1.setNotificationMsg("today is office party at 7 pm");
        data1.setNotificationDate("28-09-2018");
        arraycircularlist.add(data1);

        NotificationModel data2=new NotificationModel();
        data2.setNotificationName("Project manager");
        data2.setNotificationMsg("Whats is your work status");
        data2.setNotificationDate("10-09-2018");
        arraycircularlist.add(data2);

        NotificationModel data3=new NotificationModel();
        data3.setNotificationName("AMY Softtech");
        data3.setNotificationMsg("Whats is your work status.huhuhuhuh");
        data3.setNotificationDate("09-09-2018");
        arraycircularlist.add(data3);

        NotificationModel data4=new NotificationModel();
        data4.setNotificationName("AMY Softtech");
        data4.setNotificationMsg("Whats is your work status.huhuhuhuh");
        data4.setNotificationDate("09-09-2018");
        arraycircularlist.add(data4);

        NotificationModel dat5=new NotificationModel();
        dat5.setNotificationName("Mohit Kumar");
        dat5.setNotificationMsg("Today is Holiday");
        dat5.setNotificationDate("22-08-2018");
        arraycircularlist.add(dat5);


        NotificationModel data6=new NotificationModel();
        data6.setNotificationName("Himanshu Kumar");
        data6.setNotificationMsg("today is office party at 7 pm");
        data6.setNotificationDate("28-09-2018");
        arraycircularlist.add(data6);

        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getActivity());

        ryc_circular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        ryc_circular.setItemAnimator(new DefaultItemAnimator());

        CircularAdapter adapter=new CircularAdapter(getActivity(),arraycircularlist);
        ryc_circular.setAdapter(adapter);


        return view;
    }
}
