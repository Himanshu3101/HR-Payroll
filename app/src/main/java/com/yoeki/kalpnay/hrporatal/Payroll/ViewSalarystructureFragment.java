package com.yoeki.kalpnay.hrporatal.Payroll;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class ViewSalarystructureFragment extends Fragment implements View.OnClickListener {

    private TextView tv_viewsalaryslip;
    private RecyclerView ryc_payrollsalary;
    ArrayList<SalleryModel> salarylist;

    public ViewSalarystructureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_payroll_home, container, false);

        ryc_payrollsalary=view.findViewById(R.id.ryc_payrollsalary);

        salarylist=new ArrayList<>();

        SalleryModel data=new SalleryModel();
        data.setMonthname("March 2017");
        data.setMonthnamedetail("March");
        data.setSalaryamount("32000");

        salarylist.add(data);

        SalleryModel data2=new SalleryModel();
        data2.setMonthname("April 2017");
        data2.setMonthnamedetail("April");
        data2.setSalaryamount("25000");

        salarylist.add(data2);

        SalleryModel data3=new SalleryModel();
        data3.setMonthname("May 2017");
        data3.setMonthnamedetail("May");
        data3.setSalaryamount("32000");

        salarylist.add(data3);

        SalleryModel data4=new SalleryModel();
        data4.setMonthname("June 2017");
        data4.setMonthnamedetail("June");
        data4.setSalaryamount("40000");

        salarylist.add(data4);

        SalleryModel data5=new SalleryModel();
        data5.setMonthname("July 2017");
        data5.setMonthnamedetail("July");
        data5.setSalaryamount("20000");

        salarylist.add(data5);

        SalleryModel data6=new SalleryModel();
        data6.setMonthname("August 2017");
        data6.setMonthnamedetail("August");
        data6.setSalaryamount("32000");

        salarylist.add(data6);

        SalleryModel data7=new SalleryModel();
        data7.setMonthname("September 2017");
        data7.setMonthnamedetail("September");
        data7.setSalaryamount("50000");

        salarylist.add(data7);

        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getActivity());

        ryc_payrollsalary.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        ryc_payrollsalary.setItemAnimator(new DefaultItemAnimator());

        SalaryAdpter adapter=new SalaryAdpter(getActivity(),salarylist);
        ryc_payrollsalary.setAdapter(adapter);

       // tv_viewsalaryslip.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

        }

    }
}
