package com.yoeki.kalpnay.hrporatal.Benefits;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.Benefits.Critical.homeCritical;
import com.yoeki.kalpnay.hrporatal.Benefits.FlexibleBenefits.home_flexible;
import com.yoeki.kalpnay.hrporatal.Benefits.HealthCare.homeHealth;
import com.yoeki.kalpnay.hrporatal.Benefits.OfficeErgonomics.HomeOfficeErgonomics;
import com.yoeki.kalpnay.hrporatal.Benefits.PersonalBenefits.home_personalBenmefits;
import com.yoeki.kalpnay.hrporatal.Benefits.RetirementBenefits.home_Retirement;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.TextclassfrHeading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IACE on 13-Sep-18.
 */

public class benefit_homeRecycler extends RecyclerView.Adapter<benefit_homeRecycler.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;

    private final ArrayList<Integer> mItems = new ArrayList<>();


    public benefit_homeRecycler(Context applicationContext, List<String> benefit_lists) {
        this.mInflater = LayoutInflater.from(applicationContext);
        this.mData = benefit_lists;
        context = applicationContext;
        mItems.add(R.mipmap.health_care);
        mItems.add(R.mipmap.critical);
        mItems.add(R.mipmap.personal_benefit);
        mItems.add(R.mipmap.retirement_benefits);
        mItems.add(R.mipmap.flexible_benefit);
        mItems.add(R.mipmap.creative_benefits);
        mItems.add(R.mipmap.office_ergonomics);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.benefit_home_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String unit = mData.get(position);
        holder.benefit_name.setText(unit);

        Drawable drawable = context.getDrawable(mItems.get(position));
        Drawable rightDrawable1=context.getDrawable(R.mipmap.go);
        holder.benefit_name.setCompoundDrawablesWithIntrinsicBounds(drawable, null, rightDrawable1, null);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextclassfrHeading benefit_name;

        ViewHolder(final View itemView) {
            super(itemView);
            benefit_name = itemView.findViewById(R.id.benefit_name);

            benefit_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    String topic = benefit_name.getText().toString();
                    if(topic.equals("Health Care")){
                        intent = new Intent(context,homeHealth.class);
                        context.startActivity(intent);
                    }else if (topic.equals("Critical/Illness")){
                        intent = new Intent(context,homeCritical.class);
                        context.startActivity(intent);
                    }else if (topic.equals("Personal Benefits")){
                        intent = new Intent(context,home_personalBenmefits.class);
                        context.startActivity(intent);
                    }else if (topic.equals("Retirement Benefits")){
                        intent = new Intent(context,home_Retirement.class);
                        context.startActivity(intent);
                    }else if (topic.equals("Flexible Benefits")){
                        intent = new Intent(context,home_flexible.class);
                        context.startActivity(intent);
                    }else if (topic.equals("Creative Benefits/Perks")){

                    }else if (topic.equals("Office Ergonomics")){
                        intent = new Intent(context,HomeOfficeErgonomics.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}