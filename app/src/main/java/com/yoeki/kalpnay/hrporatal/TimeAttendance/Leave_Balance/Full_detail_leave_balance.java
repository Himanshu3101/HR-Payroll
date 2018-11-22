package com.yoeki.kalpnay.hrporatal.TimeAttendance.Leave_Balance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.util.List;

/**
 * Created by IACE on 12-Nov-18.
 */

public class Full_detail_leave_balance extends RecyclerView.Adapter<Full_detail_leave_balance.ViewHolder>  {
    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;
//    private final ArrayList<Integer> mItems = new ArrayList<>();

    public Full_detail_leave_balance(Context applicationContext, List<String> details_leaveLists) {
        this.mInflater = LayoutInflater.from(applicationContext);
        this.mData = details_leaveLists;
        context = applicationContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.balance_leave_details_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String unit = mData.get(position);

        String[] leaveDetail_break = unit.split(",");

        try{
            holder.leave_fromdte.setText(leaveDetail_break[0]);
            holder.leave_todte.setText(leaveDetail_break[1]);
            holder.leave_description.setText(leaveDetail_break[2]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Textclass leave_description,leave_fromdte,leave_todte;

        ViewHolder(final View itemView) {
            super(itemView);
            leave_fromdte = itemView.findViewById(R.id.leave_fromdte);
            leave_todte = itemView.findViewById(R.id.leave_todte);
            leave_description = itemView.findViewById(R.id.leave_description);
        }
    }
}
