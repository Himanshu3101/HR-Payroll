package com.yoeki.kalpnay.hrporatal.TimeAttendance.Holidays;

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

public class HolidayRecyclerViewAdapter extends RecyclerView.Adapter<HolidayRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;
//    private ItemClickListener mClickListener;

    // data is passed into the constructor
    HolidayRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.holiday_recycler, parent, false);
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
                holder.date.setText("N/A");
            } else {
                holder.date.setText(Break[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(Break[1].equalsIgnoreCase("null")  || Break[1].equalsIgnoreCase("") || Break[1].equalsIgnoreCase(" ")) {
            holder.dateMonth.setText("N/A");
        }else {
            holder.dateMonth.setText(Break[1]);
        }

        if(Break[2].equalsIgnoreCase("null") || Break[2].equalsIgnoreCase("") || Break[2].equalsIgnoreCase(" ")){
            holder.Event.setText("N/A");
        }else {
            holder.Event.setText(Break[2]);
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        Textclass date,dateMonth,Event;
        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            dateMonth = itemView.findViewById(R.id.dateMonth);
            Event = itemView.findViewById(R.id.Event);
        }
    }
}
