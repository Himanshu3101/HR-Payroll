package com.yoeki.kalpnay.hrporatal.Servay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class ServayHomeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_backsurvay;
    CardView card_serveyevent;
    private ArrayList<QuestionModel>questionlist;
    private ArrayList<QuestionModel>question2list;
    RecyclerView rec_question1,rec_questionlistsecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servay_home);
        initialize();

        questionlist=new ArrayList<>();
        question2list=new ArrayList<>();

        img_backsurvay.setOnClickListener(this);

        QuestionModel data=new QuestionModel();
        data.setQuestion1("What about today event.?");
        data.setQuestionunread("3 unreadquestion");
        questionlist.add(data);

        QuestionModel data1=new QuestionModel();
        data1.setQuestion1("What about today event.?");
        data1.setQuestionunread("5 unreadquestion");
        questionlist.add(data1);

        QuestionModel data2=new QuestionModel();
        data2.setQuestion1("What about management.?");
        data2.setQuestionunread("2 unreadquestion");
        questionlist.add(data2);

        QuestionModel data3=new QuestionModel();
        data3.setQuestion1("What about management.?");
        data3.setQuestionunread("7 unreadquestion");
        questionlist.add(data3);

        QuestionModel data4=new QuestionModel();
        data4.setQuestion1("What about management.?");
        data4.setQuestionunread("5 unreadquestion");
        questionlist.add(data4);

        rec_question1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        rec_question1.setItemAnimator(new DefaultItemAnimator());

        final Question1adapter adapter=new Question1adapter(ServayHomeActivity.this,questionlist);
        rec_question1.setAdapter(adapter);

        QuestionModel data7=new QuestionModel();
        data7.setQuestion2("Event");
        data7.setQuestioncount("5");
        data7.setQuestionunread("Peoples Answered");
        question2list.add(data7);

        QuestionModel data8=new QuestionModel();
        data8.setQuestion2("Training");
        data8.setQuestioncount("8");
        data8.setQuestionunread("Peoples Answered");
        question2list.add(data8);

        QuestionModel data9=new QuestionModel();
        data9.setQuestion2("Performance");
        data9.setQuestioncount("4");
        data9.setQuestionunread("Peoples Answered");
        question2list.add(data9);

        QuestionModel data10=new QuestionModel();
        data10.setQuestion2("Feedback");
        data10.setQuestioncount("9");
        data10.setQuestionunread("Peoples Answered");
        question2list.add(data10);

        rec_questionlistsecond.setLayoutManager(new LinearLayoutManager(ServayHomeActivity.this, LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        rec_questionlistsecond.setItemAnimator(new DefaultItemAnimator());

        Question2Adapter adapterr=new Question2Adapter(ServayHomeActivity.this,question2list);
        rec_questionlistsecond.setAdapter(adapterr);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_backsurvay:
                finish();
                break;
        }
    }

    public void initialize(){
        rec_question1=findViewById(R.id.rec_question1);
        img_backsurvay=findViewById(R.id.img_backsurvay);
        rec_questionlistsecond=findViewById(R.id.rec_questionlistsecond);
    }
}
