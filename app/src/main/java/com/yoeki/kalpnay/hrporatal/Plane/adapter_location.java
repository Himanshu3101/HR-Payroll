package com.yoeki.kalpnay.hrporatal.Plane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.Request.TochangeModel;

import java.util.ArrayList;
import java.util.List;

public class adapter_location extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<TochangeModel> tochangelist;

    public adapter_location(Context context, ArrayList<TochangeModel> tochangelist){

        mContext = context;
        this.tochangelist = tochangelist;
        inflater = LayoutInflater.from(mContext);
    }
    public class ViewHolder {
        TextView tv_location;
    }
    @Override
    public int getCount() {
        return tochangelist.size();
    }

    @Override
    public TochangeModel getItem(int position) {
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
            view = inflater.inflate(R.layout.adapter_location, null);
            // Locate the TextViews in listview_item.xml
            holder.tv_location = (TextView) view.findViewById(R.id.tv_location);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
            holder.tv_location.setText(tochangelist.get(position).getDepartmentname());

            notifyDataSetChanged();
        }
        return  view;
    }
}
