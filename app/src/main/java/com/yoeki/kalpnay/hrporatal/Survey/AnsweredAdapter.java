package com.yoeki.kalpnay.hrporatal.Survey;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class AnsweredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private ArrayList<AnsweredModel> answerArrayList;
    private Activity activity;

    public AnsweredAdapter(Activity activity, ArrayList<AnsweredModel> strings) {
        this.activity = activity;
        this.answerArrayList = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_answered, parent, false);
        return new ItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_answeredname.setText(answerArrayList.get(position).getAnspersonname());
        itemViewHolder.tv_answereddate.setText(answerArrayList.get(position).getAnsdate());

    }
    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return answerArrayList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_answeredname, tv_answereddate;
        CardView card_notification;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_answeredname = itemView.findViewById(R.id.tv_answeredname);
            tv_answereddate = itemView.findViewById(R.id.tv_answereddate);
        }
    }
}
