package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.List;

public class EmployeeQualificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private List<EmployeedetailModel.UserQualification> salleryList;
    private Activity activity;

    public EmployeeQualificationAdapter(Activity activity, List<EmployeedetailModel.UserQualification> strings) {
        this.activity = activity;
        this.salleryList = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_empqualification, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_empqualification.setText(salleryList.get(position).getQualification());
        itemViewHolder.tv_emppercentage.setText(salleryList.get(position).getPercentage());
        itemViewHolder.tv_empuniversity.setText(salleryList.get(position).getUniversity());

    }
    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return salleryList.size() ;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_empqualification,tv_emppercentage,tv_empuniversity;
        CardView salay_card;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_empqualification = itemView.findViewById(R.id.tv_empqualification);
            tv_emppercentage = itemView.findViewById(R.id.tv_emppercentage);
            tv_empuniversity = itemView.findViewById(R.id.tv_empuniversity);
        }
    }

}
