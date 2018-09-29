package com.yoeki.kalpnay.hrporatal.Plane;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.Notification.NotificationModel;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;

    private ArrayList<NotificationModel> stringArrayList;
    private Activity activity;

    public PlanAdapter(Activity activity, ArrayList<NotificationModel> strings){
        this.activity = activity;
        this.stringArrayList = strings;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_plan, parent, false);
        return new ItemViewHolder(itemView);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_circularname.setText(stringArrayList.get(position).getNotificationName());
        itemViewHolder.tv_circularmsg.setText(stringArrayList.get(position).getNotificationMsg());

        /*itemViewHolder.card_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,VendorMapsActivity.class);
                activity.startActivity(intent);
            }
        });*/

    }
    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_circularname, tv_circularmsg, tv_circulardate;
        CardView card_notification;

        public ItemViewHolder(View itemView) {

            super(itemView);
            tv_circularname = itemView.findViewById(R.id.tv_planname);
            tv_circularmsg = itemView.findViewById(R.id.tv_planadress);
            card_notification=itemView.findViewById(R.id.card_plan);

        }
    }
}
