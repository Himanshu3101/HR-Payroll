package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreveanceActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton img_backrequestgreveance,btn_greveancesubmit;
    private TextView tv_greveancedate,edt_greveancepersonname;
    private int mYear, mMonth, mDay;
    String strempid;
    ApiInterface apiInterface;
    EditText edt_greveancemanagername,edt_greveancepolicy,edt_greveancesolution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greveance);
             initialize();

        img_backrequestgreveance.setOnClickListener(this);
        tv_greveancedate.setOnClickListener(this);
        edt_greveancepersonname.setOnClickListener(this);
        btn_greveancesubmit.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_backrequestgreveance:
                finish();
                break;
            case R.id.tv_greveancedate:
                greveancedate();
                break;
            case R.id.edt_greveancepersonname:
                involvedpersondialog();
                break;

            case R.id.btn_greveancesubmit:

                String  user_id=null;
                user_id = preferance.getInstance(getApplicationContext()).getUserId();
                String strdate=tv_greveancedate.getText().toString();
                String strmanagername=edt_greveancemanagername.getText().toString();
                String strproce=edt_greveancepolicy.getText().toString();
                String strsolution=edt_greveancesolution.getText().toString();

                saveGreavance(user_id,strdate,strmanagername,strempid,strproce,strsolution);

                break;
        }
    }

    public void initialize(){

        img_backrequestgreveance=findViewById(R.id.img_backrequestgreveance);
        tv_greveancedate=findViewById(R.id.tv_greveancedate);
        edt_greveancepersonname=findViewById(R.id.edt_greveancepersonname);
        btn_greveancesubmit=findViewById(R.id.btn_greveancesubmit);
        edt_greveancepolicy=findViewById(R.id.edt_greveancepolicy);
        edt_greveancesolution=findViewById(R.id.edt_greveancesolution);
        edt_greveancemanagername=findViewById(R.id.edt_greveancemanagername);

    }

    public  void greveancedate(){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(GreveanceActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_greveancedate.setText(year + "/" + (monthOfYear + 1) +"/"+dayOfMonth );

                        String strtodate=year + "/" + (monthOfYear + 1) +"/"+dayOfMonth;

                        Date date2 = null;
                        Date date1=null;
                        try {
                            date1 = sdf.parse(getCurrentDateTime);
                            date2 = sdf.parse(strtodate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (date1.compareTo(date2) < 0)
                        {
                            tv_greveancedate.setText(strtodate );
                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                        }
                        else {
                            tv_greveancedate.setText(getCurrentDateTime );
                            // tv_leavereqtodate.setError("select correct date");
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void involvedpersondialog() {
        final List<GetMasterInfo.ListEmployee> listemployee= preferance.getInstance(GreveanceActivity.this).getemployelist("employee");

        final EmployeeAdapter adapter = new EmployeeAdapter(GreveanceActivity.this, listemployee);

        final Dialog dialog = new Dialog(GreveanceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.involvedperson_dialog);

        ListView listsponser = dialog.findViewById(R.id.li_involvedlist);
        listsponser.setAdapter(adapter);
        listsponser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                edt_greveancepersonname.setText(listemployee.get(i).getName());
                strempid=listemployee.get(i).getEmpId();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void saveGreavance(String UserId, String Datee, String ManagerName,String InvolvedEmpId,String Proceduree,String ProperSolution){

        final ProgressDialog progressDialog = new ProgressDialog(GreveanceActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle
        apiInterface= Api.getClient().create(ApiInterface.class);
        SaveGreavenceModel user = new SaveGreavenceModel(UserId,Datee,ManagerName,InvolvedEmpId,Proceduree,ProperSolution);
        Call<SaveGreavenceModel> call1 = apiInterface.savegreavancerequest(user);
        call1.enqueue(new Callback<SaveGreavenceModel>() {
            @Override
            public void onResponse(Call<SaveGreavenceModel> call, Response<SaveGreavenceModel> response) {

                SaveGreavenceModel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                Toast.makeText(GreveanceActivity.this, str,Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<SaveGreavenceModel> call, Throwable t) {
                call.cancel();
                String str= call.toString();
                faillerdiaolog(str);
                //Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(GreveanceActivity.this);
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
