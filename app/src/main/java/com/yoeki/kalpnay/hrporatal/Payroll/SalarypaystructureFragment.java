package com.yoeki.kalpnay.hrporatal.Payroll;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalarypaystructureFragment extends Fragment {

    ApiInterface apiInterface;
    List<Paystructuremodel.EarningDetail> arraylistearning=new ArrayList<>();
    List<Paystructuremodel.DeductionDetail> arraylistdeduction=new ArrayList<>();
    List<Paystructuremodel.TotalAmount> arraylisttotal=new ArrayList<>();
    RecyclerView ryc_deduction,ryc_earning;

    TextView tv_grosspay,tv_basicsalary,tv_totaldeduction,tv_totalearning,tv_netaamount;
    public SalarypaystructureFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_salarypaystructure, container, false);

        ryc_deduction=view.findViewById(R.id.ryc_deduction);
        ryc_earning=view.findViewById(R.id.ryc_earning);


        tv_grosspay=view.findViewById(R.id.tv_grosspay);
        tv_basicsalary=view.findViewById(R.id.tv_basicsalary);
        tv_totaldeduction=view.findViewById(R.id.tv_totaldeduction);
        tv_totalearning=view.findViewById(R.id.tv_totalearning);
        tv_netaamount=view.findViewById(R.id.tv_netaamount);

        paytructure();
        // Inflate the layout for this fragment
        return view;
    }

    public void paytructure(){


        String  user_id=null;
        user_id = preferance.getInstance(getActivity()).getUserId();
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        apiInterface= Api.getClient().create(ApiInterface.class);
        final Paystructuremodel user = new Paystructuremodel(user_id);

        Call<Paystructuremodel> call1 = apiInterface.paystructure(user);
        call1.enqueue(new Callback<Paystructuremodel>() {
            @Override
            public void onResponse(Call<Paystructuremodel> call, Response<Paystructuremodel> response) {
                Paystructuremodel user1 = response.body();
                String str=user1.getStatus();
                String strmsg=user1.getMessage();

                if (str.equals("Success")){

                    arraylistearning=user1.earningDetail;
                    arraylistdeduction=user1.deductionDetail;
                    arraylisttotal=user1.totalAmount;
                    progressDialog.dismiss();

                    tv_grosspay.setText(arraylisttotal.get(0).getGrossAmount());
                    tv_basicsalary.setText(arraylistearning.get(0).getAmount());
                    tv_totaldeduction.setText(arraylisttotal.get(0).getDeductionAmount());

                    Double totalearning=Double.parseDouble(arraylisttotal.get(0).getEarningAmount())-Double.parseDouble(arraylistearning.get(0).getAmount());

                    tv_totalearning.setText(String.valueOf(totalearning));

                    Double netamount=Double.parseDouble(arraylisttotal.get(0).getEarningAmount())-Double.parseDouble(arraylisttotal.get(0).getDeductionAmount());
                    tv_netaamount.setText(String.valueOf(netamount));

                    ryc_deduction.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    ryc_deduction.setItemAnimator(new DefaultItemAnimator());

                    DeductionAdapter adapter=new DeductionAdapter(getActivity(),arraylistdeduction);
                    ryc_deduction.setAdapter(adapter);

                    ryc_earning.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    ryc_earning.setItemAnimator(new DefaultItemAnimator());

                    arraylistearning.remove(0);

                    EarningAdapter adapterearning=new EarningAdapter(getActivity(),arraylistearning);
                    ryc_earning.setAdapter(adapterearning);

                }else{
                    faillerdiaolog(strmsg);
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Paystructuremodel> call, Throwable t) {
                call.cancel();

                progressDialog.dismiss();

            }
        });
    }

    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.failuremsg);

        TextView tv_failmsg=dialog.findViewById(R.id.tv_failmsg);
        tv_failmsg.setText(msg);

        TextView tv_cancelmsg=dialog.findViewById(R.id.tv_cancelmsg);
        tv_cancelmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
        dialog.show();
    }
}
