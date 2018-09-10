package com.yoeki.kalpnay.hrporatal.Profile.Certification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.util.List;

public class CertificationRecyclerViewAdapter extends RecyclerView.Adapter<CertificationRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;
//    private ItemClickListener mClickListener;

    // data is passed into the constructor
    CertificationRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.certification_recycler, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String unit = mData.get(position);
        String[] Break = unit.split("~");

        try {
            if (Break[0].equalsIgnoreCase("null") || Break[0].equalsIgnoreCase("") || Break[0].equalsIgnoreCase(" ")) {
                holder.CEName.setText("N/A");
            } else {
                holder.CEName.setText(Break[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(Break[1].equalsIgnoreCase("null")  || Break[1].equalsIgnoreCase("") || Break[1].equalsIgnoreCase(" ")) {
            holder.CEType.setText("N/A");
        }else {
            holder.CEType.setText(Break[1]);
        }

        if(Break[2].equalsIgnoreCase("null") || Break[2].equalsIgnoreCase("") || Break[2].equalsIgnoreCase(" ")){
            holder.CEProvider.setText("N/A");
        }else {
            holder.CEProvider.setText(Break[2]);
        }

        if(Break[3].equalsIgnoreCase("null") || Break[2].equalsIgnoreCase("") || Break[2].equalsIgnoreCase(" ")){
            holder.expired.setText("N/A");
        }else {
            holder.expired.setText(Break[3]);
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        Textclass expired,CEProvider,CEType,CEName;


        ViewHolder(View itemView) {
            super(itemView);
            CEName = itemView.findViewById(R.id.CEName);
            CEType = itemView.findViewById(R.id.CEType);
            CEProvider = itemView.findViewById(R.id.CEProvider);
            expired = itemView.findViewById(R.id.expired);

        }
    }
}
