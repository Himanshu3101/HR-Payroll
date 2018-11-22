package com.yoeki.kalpnay.hrporatal.Survey;

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

public class UnansweredFragment extends Fragment {

    private RecyclerView ryc_surveyunanswered;
    ArrayList<AnsweredModel> arraycircularlist;
    public static AnswerdFragment newInstance() {
        AnswerdFragment fragment = new AnswerdFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.unanswered_fragment, container, false);

        ryc_surveyunanswered=view.findViewById(R.id.ryc_surveyunanswered);

        arraycircularlist=new ArrayList<>();

        AnsweredModel data=new AnsweredModel();
        data.setAnspersonname("Mohit Kumar");
        data.setAnsdate("Android developer");
        arraycircularlist.add(data);

        AnsweredModel data1=new AnsweredModel();
        data1.setAnspersonname("Himanshu Kumar");
        data1.setAnsdate("Android developer");
        arraycircularlist.add(data1);

        AnsweredModel data2=new AnsweredModel();
        data2.setAnspersonname("Aarti");
        data2.setAnsdate("Android developer");
        arraycircularlist.add(data2);

        AnsweredModel data3=new AnsweredModel();
        data3.setAnspersonname("Vertika Dixit");
        data3.setAnsdate("Dot net Developer");
        arraycircularlist.add(data3);

        AnsweredModel data4=new AnsweredModel();
        data4.setAnspersonname("Mohit Kumar");
        data4.setAnsdate("Android developer");
        arraycircularlist.add(data4);

        AnsweredModel dat5=new AnsweredModel();
        dat5.setAnspersonname("Mohit Kumar");
        dat5.setAnsdate("Android developer");
        arraycircularlist.add(dat5);

        AnsweredModel data6=new AnsweredModel();
        data6.setAnspersonname("Himanshu Kumar");
        data6.setAnsdate("Android developer");
        arraycircularlist.add(data6);

        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getActivity());

        ryc_surveyunanswered.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        ryc_surveyunanswered.setItemAnimator(new DefaultItemAnimator());

        AnsweredAdapter adapter=new AnsweredAdapter(getActivity(),arraycircularlist);
        ryc_surveyunanswered.setAdapter(adapter);

        return view;
    }
}
