package com.yoeki.kalpnay.hrporatal.Payroll;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.List;

public class EarningAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private List<Paystructuremodel.EarningDetail> salleryList;
    private Activity activity;

    public EarningAdapter(Activity activity, List<Paystructuremodel.EarningDetail> strings) {
        this.activity = activity;
        this.salleryList = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_deductionearning, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_earningname.setText(salleryList.get(position).getName());
        itemViewHolder.tv_earningamount.setText(salleryList.get(position).getAmount());

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
        TextView tv_earningname,tv_earningamount;
        CardView salay_card;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_earningname = itemView.findViewById(R.id.tv_earningname);
            tv_earningamount = itemView.findViewById(R.id.tv_earningamount);
        }
    }

}
