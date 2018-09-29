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

public class DeductionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private List<Paystructuremodel.DeductionDetail> salleryList;
    private Activity activity;

    public DeductionAdapter(Activity activity, List<Paystructuremodel.DeductionDetail> strings) {
        this.activity = activity;
        this.salleryList = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_deductionamount, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_deductionname.setText(salleryList.get(position).getName());
        itemViewHolder.tv_deductionamount.setText(salleryList.get(position).getAmount());

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
        TextView tv_deductionname,tv_deductionamount;
        CardView salay_card;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_deductionname = itemView.findViewById(R.id.tv_deductionname);
            tv_deductionamount = itemView.findViewById(R.id.tv_deductionamount);
        }
    }

}
