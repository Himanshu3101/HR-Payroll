package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    private ArrayList<Searchmodel> stringArrayList;
    private Activity activity;

    public SearchAdapter(Activity activity, ArrayList<Searchmodel> strings) {
        this.activity = activity;
        this.stringArrayList = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchemploye_adapter, parent, false);
            return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tv_searchemployename.setText(stringArrayList.get(position).getEmployename());
            itemViewHolder.tv_searchemployedesignation.setText(stringArrayList.get(position).getEmployedesignation());
            itemViewHolder.tv_searchemployeemail.setText(stringArrayList.get(position).getEmployeemail());
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
        TextView tv_searchemployename,tv_searchemployedesignation,tv_searchemployeemail;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_searchemployename = itemView.findViewById(R.id.tv_searchemployename);
            tv_searchemployedesignation = itemView.findViewById(R.id.tv_searchemployedesignation);
            tv_searchemployeemail=itemView.findViewById(R.id.tv_searchemployeemail);
        }
    }
}