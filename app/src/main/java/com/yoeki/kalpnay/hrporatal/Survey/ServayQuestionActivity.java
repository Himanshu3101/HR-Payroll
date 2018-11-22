package com.yoeki.kalpnay.hrporatal.Survey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class ServayQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<QuestionModel>questionlist;
    private ArrayList<String>anslist;
    private ArrayList<String>anslist2;
    private RecyclerView ryc_question;
    private ImageView img_backsurvayquestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servay_question);
        initialize();

        questionlist=new ArrayList<>();
        anslist=new ArrayList<>();
        anslist2=new ArrayList<>();

        QuestionModel data=new QuestionModel();
        data.setQuestion("Whats about event ?");
        questionlist.add(data);

        QuestionModel data2=new QuestionModel();
        data2.setQuestion("Whats about event ?");
        questionlist.add(data2);

        QuestionModel data3=new QuestionModel();
        data3.setQuestion("Whats about event ?");
        questionlist.add(data3);

        QuestionModel data4=new QuestionModel();
        data4.setQuestion("Whats about event ?");
        questionlist.add(data4);

        QuestionModel data5=new QuestionModel();
        data5.setQuestion("Whats about event ?");
        questionlist.add(data5);

        anslist.add("average");
        anslist.add("good");
        anslist.add("excelent");
        anslist.add("very good");

        anslist2.add("good");
        anslist2.add("average");
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(this);

        ryc_question.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // rec_leavereqattachment.setLayoutManager(new Hori);
        ryc_question.setItemAnimator(new DefaultItemAnimator());

        QuestionAdapter adapter=new QuestionAdapter(ServayQuestionActivity.this,questionlist,anslist,anslist2);
        ryc_question.setAdapter(adapter);

        img_backsurvayquestion.setOnClickListener(this);

    }

    public void initialize(){
        ryc_question=findViewById(R.id.ryc_question);
        img_backsurvayquestion=findViewById(R.id.img_backsurvayquestion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.img_backsurvayquestion:
                finish();
                break;
        }
    }
}
