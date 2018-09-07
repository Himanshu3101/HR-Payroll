package com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request.ViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request.RequestRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by IACE on 06-Sep-18.
 */

public class AllRequest extends Fragment {
    RecyclerView requests_list;
    RequestRecyclerViewAdapter radapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        requests_list = getView().findViewById(R.id.requests_list);

        ArrayList<String> RequestList = new ArrayList<>();
        RequestList.add("15/Aug/18~Ashish Kumar~Sick Request");
        RequestList.add("25/Aug/18~Sachin Singh~Annual Request");
        RequestList.add("10/Sep/18~Rahul Sharma~Emergency Request");
        RequestList.add("15/Sep/18~Shahsank Tyagi~Medical Request");
        RequestList.add("15/Oct/18~Manoj Tiwari~Sick Request");
        RequestList.add("25/Nov/18~Rohit Yadav~Materanity Request");

        requests_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        radapter = new RequestRecyclerViewAdapter(  getActivity(), RequestList);
        requests_list.setAdapter(radapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_request, container, false);
    }
}
