package com.yoeki.kalpnay.hrporatal.Profile.Certification;


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

public class Certification extends Fragment {
    CertificationRecyclerViewAdapter certificateAdapter;
    private ArrayList<String> CertificationList;
    RecyclerView certificationRecycler;

    public static Certification newInstance() {
        Certification fragment = new Certification();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        CertificationList = new ArrayList<>();
        CertificationList.add("Microsoft Certified~MCP~Microsoft~05-Sep-19");
        CertificationList.add("Android Certified~SSL~Google~05-Aug-19");
        CertificationList.add("Oracle Certified~OCA~Oracle~25-Nov-19");

        certificationRecycler = getView().findViewById(R.id.certificationRecycler);
        certificationRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        certificateAdapter = new CertificationRecyclerViewAdapter( getActivity() ,CertificationList);
        certificationRecycler.setAdapter(certificateAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.certification, container, false);

    }
}
