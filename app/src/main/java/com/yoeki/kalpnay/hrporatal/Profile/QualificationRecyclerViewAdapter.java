package com.yoeki.kalpnay.hrporatal.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.List;

public class QualificationRecyclerViewAdapter extends RecyclerView.Adapter<QualificationRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;
//    private ItemClickListener mClickListener;

    // data is passed into the constructor
    QualificationRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.qualificationrecycler, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String request = mData.get(position);
        holder.name.setText(request);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;


        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.qualification);

        }
    }
}
