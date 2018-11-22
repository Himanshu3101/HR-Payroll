package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDetailActivity extends AppCompatActivity  implements View.OnClickListener{
      private ImageView img_empdetailback;
     String intentemp;
     private TextView tv_empname,tv_empdesignation,tv_empdateofbirth,tv_empphonenumber,tv_empgender,tv_empmartialstatus,tv_empdateofjoining;
     private RecyclerView ryc_empqualification,ryc_certification;
     private List<EmployeedetailModel.UserQualification>listQualification;
    private List<EmployeedetailModel.BasicUserInfo>listUserinfo;
    private List<EmployeedetailModel.UserCertification>listCirtification;
    private LinearLayout ly_empcirtification,ly_empqualification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        initialize();
        listQualification=new ArrayList<>();
        listUserinfo=new ArrayList<>();
        listCirtification=new ArrayList<>();

        intentemp=getIntent().getStringExtra("empid");

        employeedetail(intentemp);
        img_empdetailback.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.img_empdetailback:
                finish();
        }
    }

    public void initialize(){
        img_empdetailback=findViewById(R.id.img_empdetailback);
        tv_empname=findViewById(R.id.tv_empname);
        tv_empdesignation=findViewById(R.id.tv_empdesignation);
        tv_empdateofbirth=findViewById(R.id.tv_empdateofbirth);
        tv_empphonenumber=findViewById(R.id.tv_empphonenumber);
        tv_empgender=findViewById(R.id.tv_empgender);
        tv_empmartialstatus=findViewById(R.id.tv_empmartialstatus);
        tv_empdateofjoining=findViewById(R.id.tv_empdateofjoining);
        ryc_empqualification=findViewById(R.id.ryc_empqualification);
        ryc_certification=findViewById(R.id.ryc_certification);

        ly_empcirtification=findViewById(R.id.ly_empcirtification);
        ly_empqualification=findViewById(R.id.ly_empqualification);

    }

    public void employeedetail(String empid){

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(EmployeeDetailActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        ApiInterface apiInterface= Api.getClient().create(ApiInterface.class);
        final EmployeedetailModel user = new EmployeedetailModel(empid);

        Call<EmployeedetailModel> call1 = apiInterface.employeedetail(user);
        call1.enqueue(new Callback<EmployeedetailModel>() {
            @Override
            public void onResponse(Call<EmployeedetailModel> call, Response<EmployeedetailModel> response) {
                EmployeedetailModel user1 = response.body();
                String str=user1.getStatus();
                String strmsg=user1.getMessage();
                if(str.equals("Success")){
                    listUserinfo=user1.getBasicUserInfo();
                    listQualification=user1.getUserQualification();
                    listCirtification=user1.getUserCertification();
                    progressDialog.dismiss();

                    tv_empname.setText(listUserinfo.get(0).getUserName());
                    tv_empdesignation.setText(listUserinfo.get(0).getDesignation());
                    tv_empdateofbirth.setText(listUserinfo.get(0).getDOB());
                    tv_empphonenumber.setText(listUserinfo.get(0).getPesonalNumber());
                    tv_empgender.setText(listUserinfo.get(0).getPesonalNumber());
                    tv_empmartialstatus.setText(listUserinfo.get(0).getMartialStatus());
                    tv_empdateofjoining.setText(listUserinfo.get(0).getStartingDate());

                    if (listQualification.size()>0){
                        ly_empqualification.setVisibility(View.VISIBLE);
                    }else {
                        ly_empqualification.setVisibility(View.GONE);
                    }
                    if (listCirtification.size()>0){
                        ly_empcirtification.setVisibility(View.VISIBLE);
                    }else {
                        ly_empcirtification.setVisibility(View.GONE);
                    }

                    ryc_empqualification.setLayoutManager(new LinearLayoutManager(EmployeeDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                    ryc_empqualification.setItemAnimator(new DefaultItemAnimator());
                    EmployeeQualificationAdapter adapter=new EmployeeQualificationAdapter(EmployeeDetailActivity.this,listQualification);
                    ryc_empqualification.setAdapter(adapter);
                    ryc_certification.setLayoutManager(new LinearLayoutManager(EmployeeDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                    ryc_certification.setItemAnimator(new DefaultItemAnimator());
                    EmployecertificateAdapter adaptercirtification=new EmployecertificateAdapter(EmployeeDetailActivity.this,listCirtification);
                    ryc_certification.setAdapter(adaptercirtification);

                }else{
                    faillerdiaolog(strmsg);
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<EmployeedetailModel> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();

            }
        });
    }

    public  void faillerdiaolog(String msg){
        final Dialog dialog = new Dialog(EmployeeDetailActivity.this);
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
