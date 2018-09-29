package com.yoeki.kalpnay.hrporatal.Profile.Certification;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.User;
import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.UserCertification;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Certification extends Fragment {
    CertificationRecyclerViewAdapter certificateAdapter;
    private ArrayList<String> CertificationList;
    RecyclerView certificationRecycler;
    ArrayList<User> mlist;
    private static Certification instance;

    public static Certification newInstance() {
        Certification fragment = new Certification();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        CertificationList = new ArrayList<>();
        certificationRecycler = getView().findViewById(R.id.certificationRecycler);

        for (final User list : mlist) {
            for(UserCertification userCertification : list.userCertification) {

                String CertificationListString = userCertification.getNameOfCertificate()+"~"+userCertification.getType()+"~"+userCertification.getProvider()
                        +"~"+userCertification.getExpired();

                try {
                    CertificationList.add(CertificationListString);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        certificationRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        certificateAdapter = new CertificationRecyclerViewAdapter( getActivity() ,CertificationList);
        certificationRecycler.setAdapter(certificateAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.certification, container, false);
        Bundle args = getArguments();
        mlist = (ArrayList<User>) getArguments().getSerializable("keyForProfile");
        instance=this;
        return view;
    }
}
