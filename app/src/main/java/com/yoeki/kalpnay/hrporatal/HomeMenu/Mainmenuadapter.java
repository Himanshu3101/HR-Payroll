package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;

public class Mainmenuadapter extends RecyclerView.Adapter<Mainmenuadapter.MyViewHolder> {

    private ArrayList<Menuitemmodel> dataSet;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;


    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon,img_mainmenucross;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.tv_mainmenuitemname);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.img_mainmenuitemimage);
            this.img_mainmenucross=itemView.findViewById(R.id.img_mainmenucross);
           // this.card_view=itemView.findViewById(R.id.card_view);
        }
    }

    public Mainmenuadapter(Context context, ArrayList<Menuitemmodel> data) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {

       // view.setOnClickListener(HomeActivit.myOnClickListener);
if (viewType==TYPE_FOOTER){

    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.footerview, parent, false);

    MyViewHolder myViewHolder = new MyViewHolder(view);
    return myViewHolder;

      }else{
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.homeactivity_adapter, parent, false);

    MyViewHolder myViewHolder = new MyViewHolder(view);
    return myViewHolder;
      }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        ImageView imageView = holder.imageViewIcon;
        imageView.setImageBitmap(dataSet.get(listPosition).getImageid());
        holder.img_mainmenucross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataSet.remove(listPosition);
               // Toast.makeText(context, String.valueOf(listPosition), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();

            }
        });
       // notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }
}
