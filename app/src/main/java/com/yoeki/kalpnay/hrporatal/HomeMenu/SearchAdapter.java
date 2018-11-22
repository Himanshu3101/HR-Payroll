package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 2;
    private List<SearchEmployeeModel.ListAllEmployee> stringArrayList;
    private Activity activity;

    public SearchAdapter(Activity activity, List<SearchEmployeeModel.ListAllEmployee> strings) {
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
        itemViewHolder.tv_searchemployename.setText(stringArrayList.get(position).getName());
        itemViewHolder.tv_searchemployedesignation.setText(stringArrayList.get(position).getDesignation());
        itemViewHolder.tv_searchemployeemail.setText(stringArrayList.get(position).getEmailId());

        itemViewHolder.crd_searchemploye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,EmployeeDetailActivity.class);
                intent.putExtra("empid",stringArrayList.get(position).getEmpId());
                activity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;

    }
    @Override
    public int getItemCount()
    {
        return stringArrayList.size() ;
    }
    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_searchemployename,tv_searchemployedesignation,tv_searchemployeemail;
        CardView crd_searchemploye;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_searchemployename = itemView.findViewById(R.id.tv_searchemployename);
            tv_searchemployedesignation = itemView.findViewById(R.id.tv_searchemployedesignation);
            tv_searchemployeemail=itemView.findViewById(R.id.tv_searchemployeemail);
            crd_searchemploye=itemView.findViewById(R.id.crd_searchemploye);
        }
    }
}