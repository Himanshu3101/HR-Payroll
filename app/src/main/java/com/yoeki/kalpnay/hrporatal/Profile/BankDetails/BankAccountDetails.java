package com.yoeki.kalpnay.hrporatal.Profile.BankDetails;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;
import java.util.List;

public class BankAccountDetails  extends Fragment {

    private static BankAccountDetails instance;
    AppCompatTextView bankname,branchname,Accounttype;
    AppCompatButton bankselect,branchselect,Accounttypeselect;
    String Total_Bank_Account="4" +
            "" +
            "";
    String [] bank_name = {"Punjab National Bank", "OBC Bank", "HDFC Bank", "ICICI Bank"};
    String [] branch_name = {"Ghaziabad", "Noida", "Delhi", "Sikhrod"};
    String [] account_type = {"Current", "Saving", "Credit" };
    static LinearLayoutCompat selectBank;
    RecyclerView Account_selection_recycler;
    static ScrollView after_selectBank;
    Context context;
    List<String> BankName_AccountNo;

    public static BankAccountDetails newInstance() {
        BankAccountDetails fragment = new BankAccountDetails();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        bankname=(AppCompatTextView) getView().findViewById(R.id.bankname);
        branchname=(AppCompatTextView)getView().findViewById(R.id.branchname);
        Accounttype=(AppCompatTextView)getView().findViewById(R.id.Accounttype);

        bankselect=(AppCompatButton)getView().findViewById(R.id.bankselect);
        branchselect=(AppCompatButton)getView().findViewById(R.id.branchselect);
        Accounttypeselect=(AppCompatButton)getView().findViewById(R.id.Accounttypeselect);

        selectBank = (LinearLayoutCompat)getView().findViewById(R.id.selectBank);
        Account_selection_recycler = (RecyclerView)getView().findViewById(R.id.Account_selection_recycler);
        after_selectBank = (ScrollView)getView().findViewById(R.id.after_selectBank);


        if(Total_Bank_Account.equals("4")){
            selectBank.setVisibility(View.VISIBLE);
            after_selectBank.setVisibility(View.GONE);
            recyclerForTwoOrMoreAccount(view);
        }else if(Total_Bank_Account.equals("0")){
            selectBank.setVisibility(View.GONE);
            after_selectBank.setVisibility(View.VISIBLE);
        }else if(Total_Bank_Account.equals("1")){
            selectBank.setVisibility(View.GONE);
            after_selectBank.setVisibility(View.VISIBLE);
        }

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
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String Item = checkedItem.toString().replace("{","");
                                    bankname.setText(Item);
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
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String Item = checkedItem.toString().replace("{","");
                                    branchname.setText(Item);
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
                            .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String Item = checkedItem.toString().replace("{","");
                                    Accounttype.setText(Item);
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
        View view=inflater.inflate(R.layout.bank_account_details, container, false);
        instance=this;
        return view;

    }

    public void recyclerForTwoOrMoreAccount(View view){

        BankName_AccountNo = new ArrayList<>();
        BankName_AccountNo.add("Punjab National Bank~1640253614785869");
        BankName_AccountNo.add("Axis Bank~1640287453695869");
        BankName_AccountNo.add("State Bank of India~1640201236545869");
        BankName_AccountNo.add("HDFC Bank~8974553614785869");
        BankName_AccountNo.add("Oriental Bank of Commerce~314697014785869");

        try {
            Account_selection_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            BankNameAccountNo_RecyclerViewAdapter radapter = new BankNameAccountNo_RecyclerViewAdapter(  getActivity(),BankName_AccountNo);
            Account_selection_recycler.setAdapter(radapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void setBankVisibiliy(String bnk_nme, String acct_no){
        selectBank.setVisibility(View.GONE);
        after_selectBank.setVisibility(View.VISIBLE);
    }
}