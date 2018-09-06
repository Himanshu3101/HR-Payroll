package com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RequestRecyclerViewAdapter extends RecyclerView.Adapter<RequestRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;
//    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RequestRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.approve_request_recycler, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String unit = mData.get(position);
        String[] Break = unit.split("~");

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        try {
            if (Break[0].equalsIgnoreCase("null") || Break[0].equalsIgnoreCase("") || Break[0].equalsIgnoreCase(" ")) {
                holder.req_date.setText("N/A");
            } else {
                holder.req_date.setText(Break[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(Break[1].equalsIgnoreCase("null")  || Break[1].equalsIgnoreCase("") || Break[1].equalsIgnoreCase(" ")) {
            holder.employe_name.setText("N/A");
        }else {
            holder.employe_name.setText(Break[1]);
        }

        if(Break[2].equalsIgnoreCase("null") || Break[2].equalsIgnoreCase("") || Break[2].equalsIgnoreCase(" ")){
            holder.request_type.setText("N/A");
        }else {
            holder.request_type.setText(Break[2]);
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        Textclass req_date,employe_name,request_type;
        ViewHolder(View itemView) {
            super(itemView);
            req_date = itemView.findViewById(R.id.req_date);
            employe_name = itemView.findViewById(R.id.employe_name);
            request_type = itemView.findViewById(R.id.request_type);
        }
    }
}
