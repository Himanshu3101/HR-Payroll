package com.yoeki.kalpnay.hrporatal.Profile.BankDetails;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;
import com.yoeki.kalpnay.hrporatal.setting.TextclassfrHeading;

import java.util.List;

public class BankNameAccountNo_RecyclerViewAdapter extends RecyclerView.Adapter<BankNameAccountNo_RecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private static Context context;

    public BankNameAccountNo_RecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.accounts, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String unit = mData.get(position);
        String[] Break = unit.split("~");

        try {
            if (Break[0].equalsIgnoreCase("null") || Break[0].equalsIgnoreCase("") || Break[0].equalsIgnoreCase(" ")) {
                holder.bank_name.setText("N/A");
            } else {
                holder.bank_name.setText(Break[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(Break[1].equalsIgnoreCase("null")  || Break[1].equalsIgnoreCase("") || Break[1].equalsIgnoreCase(" ")) {
            holder.accountNo.setText("N/A");
        }else {
            holder.accountNo.setText(Break[1]);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextclassfrHeading bank_name;
        Textclass accountNo;
        AppCompatButton particularAC_Details;
        LinearLayoutCompat selectBank;

        ViewHolder(final View itemView) {
            super(itemView);
            bank_name = itemView.findViewById(R.id.bank_name);
            accountNo = itemView.findViewById(R.id.accountNo);
            particularAC_Details = itemView.findViewById(R.id.particularAC_Details);

            selectBank = (LinearLayoutCompat)itemView.findViewById(R.id.selectBank);

            particularAC_Details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String bnk_nme = bank_name.getText().toString();
                    String acct_no = accountNo.getText().toString();

                    try {
                        BankAccountDetails.setBankVisibiliy(bnk_nme,acct_no);
//                        selectBank.setVisibility(View.GONE);
//                        bankAccountDetails.after_selectBank.setVisibility(View.VISIBLE);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
