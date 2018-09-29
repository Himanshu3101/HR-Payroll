package com.yoeki.kalpnay.hrporatal.Profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.BasicUserInfo;
import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.User;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.util.ArrayList;

public class Personal_Info  extends Fragment {
    private static Personal_Info personal_instance;
    Textclass personal_number,code,designation,title,name,gender,martial_stat,dob,ssn;
    ArrayList<User> mlist;

    public static Personal_Info newInstance() {
        Personal_Info fragment = new Personal_Info();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        personal_number=(Textclass) getView().findViewById(R.id.personal_number);
        code=(Textclass)getView().findViewById(R.id.code);
        designation=(Textclass)getView().findViewById(R.id.designation);
        title=(Textclass)getView().findViewById(R.id.title);
        name=(Textclass)getView().findViewById(R.id.name);
        gender=(Textclass)getView().findViewById(R.id.gender);
        martial_stat = (Textclass)getView().findViewById(R.id.martial_stat);
        dob = (Textclass)getView().findViewById(R.id.dob);
        ssn = (Textclass)getView().findViewById(R.id.ssn);

        for (final User list : mlist) {
            for(BasicUserInfo basicUserInfo : list.basicUserInfo) {
                personal_number.setText(basicUserInfo.getPesonalNumber());
                code.setText(basicUserInfo.getCode());
                designation.setText(basicUserInfo.getDesignation());
                title.setText(basicUserInfo.getTitle());
                name.setText(basicUserInfo.getUserName());
                gender.setText(basicUserInfo.getGender());
                martial_stat.setText(basicUserInfo.getMartialStatus());
                dob.setText(basicUserInfo.getDOB());
                ssn.setText(basicUserInfo.getStartingDate());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.personal__info, container, false);
        Bundle args = getArguments();
        mlist = (ArrayList<User>) getArguments().getSerializable("keyForProfile");
        personal_instance=this;
        return view;
    }
}
