package com.yoeki.kalpnay.hrporatal.Servay;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 2;
    private ArrayList<QuestionModel> stringArrayList;
    private  ArrayList<String>anslist;
    private  ArrayList<String>anslist2;
    private Activity activity;

    public QuestionAdapter(Activity activity, ArrayList<QuestionModel> strings, ArrayList<String>anslist, ArrayList<String>anslist2){

        this.activity = activity;
        this.stringArrayList = strings;
        this.anslist=anslist;
        this.anslist2=anslist2;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_servayadapter, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
          ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
           int tempposition=position+1;
         itemViewHolder.tv_servayquestion.setText("Q. "+tempposition+". "+stringArrayList.get(position).getQuestion());

         if (position%2==0){
            for (int i = 0; i < anslist.size(); i++) {
                RadioButton rdbtn = new RadioButton(activity);
                rdbtn.setText(anslist.get(i));
                rdbtn.setTextColor(Color.parseColor("#696969"));
                rdbtn.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#696969")));
                itemViewHolder.rgb_servayans.addView(rdbtn);

             }
           }else {
             for (int i = 0; i < anslist2.size(); i++) {
                RadioButton rdbtn = new RadioButton(activity);
                rdbtn.setText(anslist2.get(i));
                rdbtn.setTextColor(Color.parseColor("#696969"));
                rdbtn.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#696969")));
                itemViewHolder.rgb_servayans.addView(rdbtn);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size() ;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_servayquestion;
        RadioGroup rgb_servayans;

        public ItemViewHolder(View itemView) {
            super(itemView);

            tv_servayquestion=itemView.findViewById(R.id.tv_servayquestion);
            rgb_servayans=itemView.findViewById(R.id.rgb_servayans);

        }
    }
}