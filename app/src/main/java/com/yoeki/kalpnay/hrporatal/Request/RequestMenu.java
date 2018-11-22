package com.yoeki.kalpnay.hrporatal.Request;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;
import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestMenu extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton img_backrequest;
    private LinearLayout ly_leaverequest,ly_claimreq,ly_trainningrequest,ly_greveance,ly_shiftchange,ly_advance;

    ApiInterface apiInterface;
    List<GetMasterInfo.ListTraningServiceType> arraytrainingservice=new ArrayList<>();
    List<GetMasterInfo.ListShiftMaster> arrayshift=new ArrayList<>();
    List<GetMasterInfo.ListAdvanceMaster_> arrayadvance=new ArrayList<>();
    List<GetMasterInfo.ListEmployee> arrayemploye=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_menu);

        initialize();

        img_backrequest.setOnClickListener(this);
        ly_leaverequest.setOnClickListener(this);
        ly_claimreq.setOnClickListener(this);
        ly_trainningrequest.setOnClickListener(this);
        ly_greveance.setOnClickListener(this);
        ly_shiftchange.setOnClickListener(this);
        ly_advance.setOnClickListener(this);

  //      getlistInfo();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_backrequest:
                Intent intentb = new Intent(getApplication(), HomeActivity.class);
                startActivity(intentb);
                finish();
                break;

            case R.id.ly_leaverequest:
                Intent intent=new Intent(RequestMenu.this,LeaveRequest.class);
                intent.putExtra("whereCome","fromRequest");
                startActivity(intent);
                break;
            case R.id.ly_claimreq:
                 Intent intentclaim=new Intent(RequestMenu.this,ClaimActivity.class);
                 startActivity(intentclaim);
                 break;
            case R.id.ly_trainningrequest:
                Intent intenttraining=new Intent(RequestMenu.this,TrainingActivity.class);
                startActivity(intenttraining);
                break;
            case R.id.ly_greveance:
                Intent intentgreveance=new Intent(RequestMenu.this,GreveanceActivity.class);
                startActivity(intentgreveance);
                break;
            case R.id.ly_shiftchange:
                Intent intentshiftchange=new Intent(RequestMenu.this,ShiftChange.class);
                startActivity(intentshiftchange);
                break;
            case R.id.ly_advance:
                Intent intentadvance=new Intent(RequestMenu.this,AdvanceActivity.class);
                startActivity(intentadvance);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplication(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public  void initialize(){

        ly_advance=findViewById(R.id.ly_advance);
        img_backrequest=findViewById(R.id.img_backrequest);
        ly_leaverequest=findViewById(R.id.ly_leaverequest);
        ly_claimreq=findViewById(R.id.ly_claimreq);
        ly_trainningrequest=findViewById(R.id.ly_trainningrequest);
        ly_greveance=findViewById(R.id.ly_greveance);
        ly_shiftchange=findViewById(R.id.ly_shiftchange);

    }

    public void getlistInfo(){

        String  user_id=null;
        user_id = preferance.getInstance(RequestMenu.this).getUserId();
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(RequestMenu.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        apiInterface= Api.getClient().create(ApiInterface.class);
        final GetMasterInfo user = new GetMasterInfo(user_id);

        Call<GetMasterInfo> call1 = apiInterface.saveallrequestlist(user);
        call1.enqueue(new Callback<GetMasterInfo>() {
            @Override
            public void onResponse(Call<GetMasterInfo> call, Response<GetMasterInfo> response) {
                GetMasterInfo user1 = response.body();
                String str=user1.getStatus();
                String strmsg=user1.getMessage();

                if (str.equals("Success")){

                    arraytrainingservice=user1.getListTraningServiceType();
                    arrayshift=user1.getListShiftMaster();
                    arrayadvance=user1.getListAdvanceMaster();
                    arrayemploye=user1.getListEmployee();
                    preferance.getInstance(getApplicationContext()).savetrainingcourseArrayList(arraytrainingservice,"trainingcourse");
                    preferance.getInstance(getApplicationContext()).saveshift(arrayshift,"shiftmaster");
                    preferance.getInstance(getApplicationContext()).saveadvancelist(arrayadvance,"advance");
                    preferance.getInstance(getApplicationContext()).saveemploye(arrayemploye,"employee");
                    progressDialog.dismiss();
                }else{
                    faillerdiaolog(strmsg);
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<GetMasterInfo> call, Throwable t) {
                call.cancel();

                progressDialog.dismiss();

            }
        });
    }
    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(RequestMenu.this);
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
