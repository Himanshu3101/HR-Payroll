package com.yoeki.kalpnay.hrporatal.Profile.BankDetails;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.User;
import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.UserBankDetail;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Edittextclass;

import java.util.ArrayList;

public class BankAccountDetails  extends Fragment {

    private static BankAccountDetails instance;
    static Edittextclass bankname,account_no, branchname, Accounttype, ifsc;
    AppCompatButton bankselect,branchselect,Accounttypeselect,submit;
    static ArrayList<String> Total_Bank_Account;
    ArrayList<String> bank_name;
    ArrayList<String> branch_name;
    ArrayList<String> account_type;
    static LinearLayoutCompat selectBank;
    RecyclerView Account_selection_recycler;
    static ScrollView after_selectBank;
    ArrayList<User> mlist;
    String bankDetailsArray;

    public static BankAccountDetails newInstance() {
        BankAccountDetails fragment = new BankAccountDetails();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        account_no=(Edittextclass) getView().findViewById(R.id.account_no);
        ifsc=(Edittextclass) getView().findViewById(R.id.ifsc);
        bankname=(Edittextclass) getView().findViewById(R.id.bankname);
        branchname=(Edittextclass)getView().findViewById(R.id.branchname);
        Accounttype=(Edittextclass)getView().findViewById(R.id.Accounttype);

        bankselect=(AppCompatButton)getView().findViewById(R.id.bankselect);
        branchselect=(AppCompatButton)getView().findViewById(R.id.branchselect);
        Accounttypeselect=(AppCompatButton)getView().findViewById(R.id.Accounttypeselect);
        submit=(AppCompatButton)getView().findViewById(R.id.submit);

        selectBank = (LinearLayoutCompat)getView().findViewById(R.id.selectBank);
        Account_selection_recycler = (RecyclerView)getView().findViewById(R.id.Account_selection_recycler);
        after_selectBank = (ScrollView)getView().findViewById(R.id.after_selectBank);

        Total_Bank_Account = new ArrayList<String>();
        bank_name= new ArrayList<String>();
        branch_name= new ArrayList<String>();
        account_type= new ArrayList<String>();

        for (final User list : mlist) {
            for(UserBankDetail bankDetails : list.userBankDetail) {

                bankDetailsArray = bankDetails.getBankName()+"~"+bankDetails.getAccountNo()+"~"+bankDetails.getAccountType()
                        +"~"+bankDetails.getBranchName()+"~"+bankDetails.getIFSCCode()+"~"+bankDetails.getBranchAddress();

                try {
                    bank_name.add(bankDetails.getBankName());
                    branch_name.add(bankDetails.getBranchName());
                    account_type.add(bankDetails.getAccountType());
                    Total_Bank_Account.add(bankDetailsArray);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


        if(Total_Bank_Account.size()==0){
            selectBank.setVisibility(View.GONE);
            after_selectBank.setVisibility(View.VISIBLE);
        }else if(Total_Bank_Account.size()==1){
            selectBank.setVisibility(View.GONE);
            after_selectBank.setVisibility(View.VISIBLE);
            String[] splited =  bankDetailsArray.split("~");
            bankname.setText(splited[0]);
            account_no.setText(splited[1]);
            branchname.setText(splited[3]);
            Accounttype.setText(splited[2]);
            ifsc.setText(splited[4]);

        }else{
            selectBank.setVisibility(View.VISIBLE);
            after_selectBank.setVisibility(View.GONE);
            recyclerForTwoOrMoreAccount(view);
        }
            bankselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AlertDialog.Builder(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                            .setTitle("Select Bank Name")
                            .setSingleChoiceItems((ListAdapter) bank_name, 0, null)
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
                            .setSingleChoiceItems((ListAdapter) branch_name, 0, null)
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
                            .setSingleChoiceItems((ListAdapter) account_type, 0, null)
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bank_account_details, container, false);
        Bundle args = getArguments();
        mlist = (ArrayList<User>) getArguments().getSerializable("keyForProfile");
        instance=this;
        return view;

    }

    public void recyclerForTwoOrMoreAccount(View view){
        try {
            Account_selection_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            BankNameAccountNo_RecyclerViewAdapter radapter = new BankNameAccountNo_RecyclerViewAdapter(  getActivity(),Total_Bank_Account);
            Account_selection_recycler.setAdapter(radapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void setBankVisibiliy(String bnk_nme, String acct_no){
        selectBank.setVisibility(View.GONE);
        after_selectBank.setVisibility(View.VISIBLE);
        for(int i =0;i<= Total_Bank_Account.size();i++){
            String acct = Total_Bank_Account.get(i);
            String[] account = acct.split("~");
            if(acct_no.equals(account[1])){
                bankname.setText(account[0]);
                account_no.setText(account[1]);
                branchname.setText(account[3]);
                Accounttype.setText(account[2]);
                ifsc.setText(account[4]);
            }
        }
    }
}