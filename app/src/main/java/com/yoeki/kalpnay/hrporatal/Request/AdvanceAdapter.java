package com.yoeki.kalpnay.hrporatal.Request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.List;

public class AdvanceAdapter  extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<GetMasterInfo.ListAdvanceMaster_> tochangelist;

    public AdvanceAdapter(Context context, List<GetMasterInfo.ListAdvanceMaster_> tochangelist){
        mContext = context;
        this.tochangelist = tochangelist;
        inflater = LayoutInflater.from(mContext);
    }

    public class ViewHolder {
        TextView departmentname;
    }
    @Override
    public int getCount() {
        return tochangelist.size();
    }

    @Override
    public GetMasterInfo.ListAdvanceMaster_ getItem(int position) {
        return tochangelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.tochange_adapter, null);
            // Locate the TextViews in listview_item.xml
            holder.departmentname = (TextView) view.findViewById(R.id.tv_changedepartmentname);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
            holder.departmentname.setText(tochangelist.get(position).getName());

            notifyDataSetChanged();
        }
        return  view;
    }
}

