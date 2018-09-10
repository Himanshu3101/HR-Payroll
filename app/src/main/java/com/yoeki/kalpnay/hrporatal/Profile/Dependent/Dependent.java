package com.yoeki.kalpnay.hrporatal.Profile.Dependent;


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

public class Dependent extends Fragment {

    DependentRecyclerViewAdapter dependentAdapter;
    private ArrayList<String> DependentList;
    RecyclerView dependentRecycler;

    public static Dependent newInstance() {
        Dependent fragment = new Dependent();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        DependentList = new ArrayList<>();
        DependentList.add("Rahul Sharma~Father~rahul1@ghmail.com~9874585214");
        DependentList.add("Abhishek Yadav~Husband~abhishek1@ghmail.com~8521476541");
        DependentList.add("Rashmi singh~Son~rashmi1@ghmail.com~7412589635");

        dependentRecycler = getView().findViewById(R.id.dependentRecycler);
        dependentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        dependentAdapter = new DependentRecyclerViewAdapter( getActivity() ,DependentList);
        dependentRecycler.setAdapter(dependentAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dependent, container, false);

    }
}
