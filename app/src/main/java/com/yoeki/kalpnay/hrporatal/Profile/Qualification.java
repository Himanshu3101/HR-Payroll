package com.yoeki.kalpnay.hrporatal.Profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Qualification extends Fragment {
    QualificationRecyclerViewAdapter qadapter;
    private ArrayList<String> QualificationList;
    RecyclerView qualificationRecycler;
    public static Qualification newInstance() {
        Qualification fragment = new Qualification();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        QualificationList = new ArrayList<>();
        QualificationList.add("Qualification");
        QualificationList.add("Qualification");
        QualificationList.add("Qualification");
        QualificationList.add("Qualification");


        qualificationRecycler = getView().findViewById(R.id.qualificationrecycler);
        qualificationRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        qadapter = new QualificationRecyclerViewAdapter( getActivity() ,QualificationList);
        qualificationRecycler.setAdapter(qadapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qualification, container, false);

    }
}
