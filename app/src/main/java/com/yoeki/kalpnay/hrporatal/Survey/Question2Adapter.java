package com.yoeki.kalpnay.hrporatal.Survey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Question2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private ArrayList<QuestionModel> questionlist;
    private Activity activity;
    Context context;
    public Question2Adapter(Activity activity, ArrayList<QuestionModel> questionlist) {
        this.activity = activity;
        this.questionlist = questionlist;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_question2, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.tv_question2.setText(questionlist.get(position).getQuestion2());
        itemViewHolder.tv_unreadquestion2count.setText(questionlist.get(position).getQuestioncount());
        itemViewHolder.tv_unreadquestion2.setText(questionlist.get(position).getQuestionunread());

        itemViewHolder.card_serveyevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, EventActivity.class);
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
        TextView tv_question2,tv_unreadquestion2count,tv_unreadquestion2;
        CardView card_serveyevent;
        AppCompatButton answers;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_question2 = itemView.findViewById(R.id.tv_question2);
            tv_unreadquestion2count = itemView.findViewById(R.id.tv_unreadquestion2count);
            tv_unreadquestion2 = itemView.findViewById(R.id.tv_unreadquestion2);
            card_serveyevent=itemView.findViewById(R.id.card_serveyevent);
//            answers=itemView.findViewById(R.id.answers);

//            answers.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, Question_surevey.class);
//                    context.startActivity(intent);
//                }
//            });
        }

    }

}
