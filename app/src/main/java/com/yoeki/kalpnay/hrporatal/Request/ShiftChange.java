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
import android.widget.Button;
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

public class ShiftChange extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton img_backreqshiftchange;
    private TextView tv_shiftchangedate,tv_shiftchangetodep,tv_shiftchangecurrentshift;
    private int mYear, mMonth, mDay;
    String timingidto,timingidcurrent;
    private EditText edt_shiftchangereason;
    ApiInterface apiInterface;
    private Button btn_shiftchangesubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_change);

        initialize();

        img_backreqshiftchange.setOnClickListener(this);
        tv_shiftchangedate.setOnClickListener(this);
        tv_shiftchangetodep.setOnClickListener(this);
        tv_shiftchangecurrentshift.setOnClickListener(this);
        btn_shiftchangesubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_backreqshiftchange:
                finish();
                break;
            case R.id.tv_shiftchangedate:
                shiftdate();
                break;
            case R.id.tv_shiftchangetodep:
                tochangedepartmentdialog("1");
                break;
            case R.id.tv_shiftchangecurrentshift:
                tochangedepartmentdialog("2");
                break;
            case R.id.btn_shiftchangesubmit:

                String  user_id=null;
                user_id = preferance.getInstance(getApplicationContext()).getUserId();
                String strdate=tv_shiftchangedate.getText().toString();
                String strreasone=edt_shiftchangereason.getText().toString();

                saveShift(user_id,strdate,timingidcurrent,timingidto,strreasone);

                break;
        }
    }

    public void initialize() {
        img_backreqshiftchange = findViewById(R.id.img_backreqshiftchange);
        tv_shiftchangedate = findViewById(R.id.tv_shiftchangedate);
        tv_shiftchangetodep=findViewById(R.id.tv_shiftchangetodep);
        tv_shiftchangecurrentshift=findViewById(R.id.tv_shiftchangecurrentshift);
        edt_shiftchangereason=findViewById(R.id.edt_shiftchangereason);

        btn_shiftchangesubmit=findViewById(R.id.btn_shiftchangesubmit);
    }
    public void shiftdate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(ShiftChange.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_shiftchangedate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                        String strtodate = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;

                        Date date2 = null;
                        Date date1 = null;
                        try {
                            date1 = sdf.parse(getCurrentDateTime);
                            date2 = sdf.parse(strtodate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (date1.compareTo(date2) < 0) {
                            tv_shiftchangedate.setText(strtodate);
                        } else {
                            tv_shiftchangedate.setText(getCurrentDateTime);
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void tochangedepartmentdialog(final String idtemp) {

        final List<GetMasterInfo.ListShiftMaster> listshift= preferance.getInstance(ShiftChange.this).getshiftmaster("shiftmaster");

        final Dialog dialog = new Dialog(ShiftChange.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.tochangeshitdialog);

        ListView listsponser = dialog.findViewById(R.id.li_tochangelist);

        if (listshift.size()>0){
            final ShiftAdapter adapter = new ShiftAdapter(ShiftChange.this, listshift);
            listsponser.setAdapter(adapter);
        }

        listsponser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              if (idtemp.equals("1")){
                  tv_shiftchangetodep.setText(listshift.get(i).getName());
                  timingidto=listshift.get(i).getShiftId();
              }else if (idtemp.equals("2")){
                  timingidcurrent=listshift.get(i).getShiftId();
                  tv_shiftchangecurrentshift.setText(listshift.get(i).getName());
              }
                dialog.dismiss();

            }
        });

        dialog.show();
    }


    public void saveShift(String UserId, String Datee, String currentshiftid,String changeshitid,String reason){

        final ProgressDialog progressDialog = new ProgressDialog(ShiftChange.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        Shiftchangemodel user = new Shiftchangemodel(UserId,Datee,currentshiftid,changeshitid,reason);

        Call<Shiftchangemodel> call1 = apiInterface.saveshiftrequest(user);
        call1.enqueue(new Callback<Shiftchangemodel>() {
            @Override
            public void onResponse(Call<Shiftchangemodel> call, Response<Shiftchangemodel> response) {

                Shiftchangemodel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                Toast.makeText(ShiftChange.this, str, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<Shiftchangemodel> call, Throwable t) {
                call.cancel();
                String str= call.toString();
                faillerdiaolog(str);
                //   Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(ShiftChange.this);
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