package com.yoeki.kalpnay.hrporatal.Profile.Dependent;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.User;
import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.UserDependent;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Dependent extends Fragment {

    DependentRecyclerViewAdapter dependentAdapter;
    private ArrayList<String> DependentList;
    RecyclerView dependentRecycler;
    ArrayList<User> mlist;
    private static Dependent instance;

    public static Dependent newInstance() {
        Dependent fragment = new Dependent();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        DependentList = new ArrayList<>();
        dependentRecycler = getView().findViewById(R.id.dependentRecycler);

        for (final User list : mlist) {
            for(UserDependent userDependent : list.userDependents) {

                String dependentListString = userDependent.getName()+"~"+userDependent.getRelation()+"~"+userDependent.getContactNo();

                try {
                    DependentList.add(dependentListString);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        dependentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        dependentAdapter = new DependentRecyclerViewAdapter( getActivity() ,DependentList);
        dependentRecycler.setAdapter(dependentAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dependent, container, false);
        Bundle args = getArguments();
        mlist = (ArrayList<User>) getArguments().getSerializable("keyForProfile");
        instance=this;
        return view;
    }
}
