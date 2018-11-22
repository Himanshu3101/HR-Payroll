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

public class EmployecertificateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private List<EmployeedetailModel.UserCertification> salleryList;
    private Activity activity;

    public EmployecertificateAdapter(Activity activity, List<EmployeedetailModel.UserCertification> strings) {

        this.activity = activity;
        this.salleryList = strings;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_empcertification, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_empcertificatename.setText(salleryList.get(position).getNameOfCertificate());
        itemViewHolder.tv_empcertificatetype.setText(salleryList.get(position).getType());
        itemViewHolder.tv_empcirticprovider.setText(salleryList.get(position).getProvider());

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
        TextView tv_empcertificatename,tv_empcertificatetype,tv_empcirticprovider;
        CardView salay_card;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_empcertificatename = itemView.findViewById(R.id.tv_empcertificatename);
            tv_empcertificatetype = itemView.findViewById(R.id.tv_empcertificatetype);
            tv_empcirticprovider = itemView.findViewById(R.id.tv_empcirticprovider);
        }
    }

}
