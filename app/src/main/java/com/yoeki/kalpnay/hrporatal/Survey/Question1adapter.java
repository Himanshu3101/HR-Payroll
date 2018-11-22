package com.yoeki.kalpnay.hrporatal.Survey;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.Question_surevey;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Question1adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private ArrayList<QuestionModel> questionlist;
    private Activity activity;

    public Question1adapter(Activity activity, ArrayList<QuestionModel> questionlist) {
        this.activity = activity;
        this.questionlist = questionlist;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question1adapter_servay, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_question1.setText(questionlist.get(position).getQuestion1());
        itemViewHolder.tv_unreadquestion1.setText(questionlist.get(position).getQuestionunread());

        itemViewHolder.card_upperQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quest = itemViewHolder.tv_question1.getText().toString();
                Intent intent=new Intent(activity, Question_surevey.class);
                intent.putExtra("question",quest);
                activity.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;
    }
    @Override
    public int getItemCount() {
        return questionlist.size() ;
    }
    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_question1,tv_unreadquestion1;
        CardView card_upperQuest;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_question1 = itemView.findViewById(R.id.tv_question1);
            tv_unreadquestion1 = itemView.findViewById(R.id.tv_unreadquestion1);
            card_upperQuest = itemView.findViewById(R.id.card_upperQuest);
        }
    }

}
