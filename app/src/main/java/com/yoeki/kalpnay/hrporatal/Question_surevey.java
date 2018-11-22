package com.yoeki.kalpnay.hrporatal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.yoeki.kalpnay.hrporatal.Survey.ServayHomeActivity;
import com.yoeki.kalpnay.hrporatal.setting.TextclassfrHeading;

/**
 * Created by IACE on 18-Sep-18.
 */

public class Question_surevey extends AppCompatActivity implements SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {
    SeekBar seekBar;
    SmileRating mSmileRating;
    TextclassfrHeading question_coming;
    Button survey_answerBack,answerSurvey_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_survey);

        String question =  getIntent().getStringExtra("question");

        question_coming = (TextclassfrHeading)findViewById(R.id.question_coming);
        mSmileRating = (SmileRating) findViewById(R.id.question_Answer);
        survey_answerBack = (Button)findViewById(R.id.survey_answerBack);
        answerSurvey_submit = (Button)findViewById(R.id.answerSurvey_submit);
        mSmileRating.setOnSmileySelectionListener(this);
        mSmileRating.setOnRatingSelectedListener(this);
        mSmileRating.setSelectedSmile(BaseRating.GOOD);

        question_coming.setText(question);

        survey_answerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServayHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        answerSurvey_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Question_surevey.this, "Answer Submitted Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ServayHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onRatingSelected(int level, boolean reselected) {
//        Toast.makeText(this, "Rated as: " + level + " - " + reselected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSmileySelected(int smiley, boolean reselected) {
        switch (smiley) {
            case SmileRating.BAD:
                Toast.makeText(this, "Bad", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.GOOD:
                Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.GREAT:
                Toast.makeText(this, "Great", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.OKAY:
                Toast.makeText(this, "Okay", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.TERRIBLE:
                Toast.makeText(this, "Terrible", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.NONE:
                Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ServayHomeActivity.class);
        startActivity(intent);
        finish();
    }
}
