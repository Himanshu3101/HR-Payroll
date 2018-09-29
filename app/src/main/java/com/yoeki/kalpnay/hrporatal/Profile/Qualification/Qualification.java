package com.yoeki.kalpnay.hrporatal.Profile.Qualification;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.User;
import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.UserQualification;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Qualification extends Fragment {
    private static Qualification instance;
    QualificationRecyclerViewAdapter qadapter;
    private ArrayList<String> QualificationList;
    RecyclerView qualificationRecycler;
    ArrayList<User> mlist;

    public static Qualification newInstance() {
        Qualification fragment = new Qualification();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        qualificationRecycler = getView().findViewById(R.id.qualificationrecycler);
        QualificationList = new ArrayList<>();
        for (final User list : mlist) {
            for(UserQualification qualificationDetails : list.userQualification) {

                String qualificationDetailsArray = qualificationDetails.getQualification()+"~"+qualificationDetails.getUniversity()+"~"+qualificationDetails.getPercentage()
                        +"~"+qualificationDetails.getSession();
                QualificationList.add(qualificationDetailsArray);
            }
        }

        qualificationRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        qadapter = new QualificationRecyclerViewAdapter( getActivity() ,QualificationList);
        qualificationRecycler.setAdapter(qadapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.qualification, container, false);
        Bundle args = getArguments();
        mlist = (ArrayList<User>) getArguments().getSerializable("keyForProfile");
        instance=this;
        return view;

    }
}
