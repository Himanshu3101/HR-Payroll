package com.yoeki.kalpnay.hrporatal.Request;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.HomeMenu.Menuitemmodel;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Attachmentview_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    private ArrayList<Menuitemmodel> stringArrayList;
    private Activity activity;

    public Attachmentview_adapter(Activity activity, ArrayList<Menuitemmodel> strings) {
        this.activity = activity;
        this.stringArrayList = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homeactivity_adapter, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footerview, parent, false);
            return new FooterViewHolder(itemView);
        } else return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.footerimage.setImageResource(R.drawable.plusicon_new);
           /* footerHolder.footerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "You clicked at Footer View", Toast.LENGTH_SHORT).show();
                }
            });*/
        } else if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
           itemViewHolder.imageViewIcon.setImageBitmap(stringArrayList.get(position).getImageid());
           itemViewHolder.img_mainmenucross.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   stringArrayList.remove(position);
                   // Toast.makeText(context, String.valueOf(listPosition), Toast.LENGTH_SHORT).show();
                   notifyDataSetChanged();
               }
           });
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == stringArrayList.size()) {
            return TYPE_FOOTER;
        }
            return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size()+1;
    }
    private class FooterViewHolder extends RecyclerView.ViewHolder {
        ImageView footerimage;
        public FooterViewHolder(View view) {
            super(view);
            footerimage = (ImageView) view.findViewById(R.id.img_footer);

            footerimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(activity instanceof LeaveRequest){
                        ((LeaveRequest)activity).uploadattachmentdialog();
                    }else if (activity instanceof ClaimActivity){
                        ((ClaimActivity)activity).uploadattachmentdialog();
                    }
                }
            });
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;

        ImageView imageViewIcon,img_mainmenucross;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageViewIcon =itemView.findViewById(R.id.img_mainmenuitemimage);
            img_mainmenucross=itemView.findViewById(R.id.img_mainmenucross);
        }
    }
}