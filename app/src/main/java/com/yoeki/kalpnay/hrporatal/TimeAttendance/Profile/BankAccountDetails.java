package com.yoeki.kalpnay.hrporatal.TimeAttendance.Profile;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.Arrays;

public class BankAccountDetails  extends Fragment {

    AppCompatTextView bankname,branchname,Accounttype;
    AppCompatButton bankselect,branchselect,Accounttypeselect;

    String [] bank_name = {"Punjab National Bank", "OBC Bank", "HDFC Bank", "ICICI Bank"};
    String [] branch_name = {"Ghaziabad", "Noida", "Delhi", "Sikhrod"};
    String [] account_type = {"Current", "Saving", "Credit" };

    public static BankAccountDetails newInstance() {
        BankAccountDetails fragment = new BankAccountDetails();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        bankname=(AppCompatTextView) getView().findViewById(R.id.bankname);
        branchname=(AppCompatTextView)getView().findViewById(R.id.branchname);
        Accounttype=(AppCompatTextView)getView().findViewById(R.id.Accounttype);

        bankselect=(AppCompatButton)view.findViewById(R.id.bankselect);
        branchselect=(AppCompatButton)getView().findViewById(R.id.branchselect);
        Accounttypeselect=(AppCompatButton)getView().findViewById(R.id.Accounttypeselect);

        bankselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AlertDialog.Builder(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                            .setTitle("Select Bank Name")
                            .setSingleChoiceItems(bank_name, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    branchname.setText(Arrays.toString(bank_name));
                                }
                            })
                            .show();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        branchselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AlertDialog.Builder(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                            .setTitle("Select Branch Name")
                            .setSingleChoiceItems(branch_name, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    branchname.setText(Arrays.toString(branch_name));
                                }
                            })
                            .show();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        Accounttypeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AlertDialog.Builder(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                            .setTitle("Select Account Type")
                            .setSingleChoiceItems(account_type, 0, null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    Accounttype.setText(Arrays.toString(account_type));
                                }
                            })
                            .show();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bank_account_details, container, false);


    }
}