package com.yoeki.kalpnay.hrporatal.TimeAttendance.Leave_Balance;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.TextclassfrHeading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IACE on 10-Nov-18.
 */

public class leave_balanceRecycler extends RecyclerView.Adapter<leave_balanceRecycler.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;
    private final ArrayList<Integer> mItems = new ArrayList<>();

    public leave_balanceRecycler(Context applicationContext, List<String> balance_lists) {
        this.mInflater = LayoutInflater.from(applicationContext);
        this.mData = balance_lists;
        context = applicationContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.leave_balance_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String unit = mData.get(position);

        String[] unit_break = unit.split(",");

        try{
            holder.count_leaveBalance.setText(unit_break[0]);
            holder.leave_heading.setText(unit_break[1]);
        }catch (Exception e){
            e.printStackTrace();
        }
        Drawable rightDrawable1=context.getDrawable(R.mipmap.go);
        holder.leave_heading.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDrawable1, null);

        holder.card_serveyevent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.topic = holder.leave_heading.getText().toString();
                    Intent intent = new Intent(context,popupLeave_Balance.class);
                    intent.putExtra("Leave Type",holder.topic);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextclassfrHeading leave_heading,count_leaveBalance;
        String topic;
        CardView card_serveyevent;
        AppCompatButton kk_leaveDetails;

        ViewHolder(final View itemView) {
            super(itemView);
            leave_heading = itemView.findViewById(R.id.leave_head);
            count_leaveBalance = itemView.findViewById(R.id.count_leaveBalance);
            kk_leaveDetails = itemView.findViewById(R.id.kk_leaveDetails);
            card_serveyevent=itemView.findViewById(R.id.card_serveyevent);
        }
    }
}



