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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
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

public class AdvanceActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ly_advancefortravelview,ly_advancetypeother,ly_advanceforhotelviewview,ly_advanceamount;
    private TextView tv_advancereqdate,tv_advancetravelfromdate,tv_advancefortraveltodate,tv_advancetraveltrancporttype,tv_advancehotelfromdate
            ,tv_advancehotelviewtodate,tv_advancereqsubmit;
    private AppCompatButton img_backrequestadvance;
    private int mYear, mMonth, mDay,mHour,mMinute;
    String str="temp",advanceId;
    private  Button btn_advancereqsubmit;
    String strtravel="temp";
    private EditText edt_advancereqdescription,edt_advancehotelviewamount,tv_advancereqforadvance;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        initialize();

        tv_advancereqdate.setOnClickListener(this);
        tv_advancereqforadvance.setOnClickListener(this);
        tv_advancetravelfromdate.setOnClickListener(this);
        tv_advancefortraveltodate.setOnClickListener(this);
        tv_advancetraveltrancporttype.setOnClickListener(this);
        tv_advancehotelfromdate.setOnClickListener(this);
        tv_advancehotelviewtodate.setOnClickListener(this);
        btn_advancereqsubmit.setOnClickListener(this);
        img_backrequestadvance.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_advancereqdate:
                advancedate("1");
                break;
           /* case R.id.tv_advancereqforadvance:
               // advancefordialog();
                advanceforlistdialog();
                break;*/
            case R.id.tv_advancetravelfromdate:
                advancedate("2");
                break;
            case R.id.tv_advancefortraveltodate:
                advancedate("3");
                break;
            case R.id.tv_advancehotelfromdate:
                advancedate("4");
                break;
            case R.id.tv_advancehotelviewtodate:
                advancedate("5");
                break;

            case R.id.tv_advancetraveltrancporttype:
               // advanceforlistdialog();
                advancetraveltypedialog();
              break;

            case R.id.img_backrequestadvance:
                finish();
                break;
            case R.id.btn_advancereqsubmit:
                String  user_id=null;
                user_id = preferance.getInstance(getApplicationContext()).getUserId();
                String strdate=tv_advancereqdate.getText().toString();
                String strfromdate=tv_advancehotelfromdate.getText().toString();
                String strtodate=tv_advancehotelviewtodate.getText().toString();
                String strdescription=edt_advancereqdescription.getText().toString();
                String stramount=edt_advancehotelviewamount.getText().toString();
                saveAdvanced(user_id,strdate,"1",strfromdate,strtodate,"",strdescription,stramount);
                break;
        }
    }

    public  void initialize(){
        ly_advancefortravelview=findViewById(R.id.ly_advancefortravelview);
        ly_advancetypeother=findViewById(R.id.ly_advancetypeother);
        ly_advanceforhotelviewview=findViewById(R.id.ly_advanceforhotelviewview);
        ly_advanceamount=findViewById(R.id.ly_advanceamount);
        tv_advancereqdate=findViewById(R.id.tv_advancereqdate);
        tv_advancereqforadvance=findViewById(R.id.tv_advancereqforadvance);
        tv_advancetravelfromdate=findViewById(R.id.tv_advancetravelfromdate);
        tv_advancefortraveltodate=findViewById(R.id.tv_advancefortraveltodate);
        tv_advancetraveltrancporttype=findViewById(R.id.tv_advancetraveltrancporttype);
        tv_advancehotelfromdate=findViewById(R.id.tv_advancehotelfromdate);
        tv_advancehotelviewtodate=findViewById(R.id.tv_advancehotelviewtodate);
        btn_advancereqsubmit=findViewById(R.id.btn_advancereqsubmit);
        img_backrequestadvance=findViewById(R.id.img_backrequestadvance);

        edt_advancereqdescription=findViewById(R.id.edt_advancereqdescription);
        edt_advancehotelviewamount=findViewById(R.id.edt_advancehotelviewamount);

    }

    public  void advancedate(final String strdate){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(AdvanceActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                      //  tv_advancereqdate.setText(year + "-" + (monthOfYear + 1) +"-"+dayOfMonth );
                        String strtodate=year + "/" + (monthOfYear + 1) +"/"+dayOfMonth;

                        Date date2 = null;
                        Date date1=null;
                        try {
                            date1 = sdf.parse(getCurrentDateTime);
                            date2 = sdf.parse(strtodate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (strdate.equals("1")){
                            if (date1.compareTo(date2) < 0)
                            {
                                tv_advancereqdate.setText(strtodate );
                                // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                            }
                            else {
                                tv_advancereqdate.setText(getCurrentDateTime );
                                // tv_leavereqtodate.setError("select correct date");
                            }
                        }else if (strdate.equals("2")){
                            if (date1.compareTo(date2) < 0)
                            {
                                tv_advancetravelfromdate.setText(strtodate );
                                // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                            }
                            else {
                                tv_advancetravelfromdate.setText(getCurrentDateTime );
                                // tv_leavereqtodate.setError("select correct date");
                            }
                        }else if (strdate.equals("3")){
                            if (date1.compareTo(date2) < 0)
                            {
                                tv_advancefortraveltodate.setText(strtodate );
                                // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                            }
                            else {
                                tv_advancefortraveltodate.setText(getCurrentDateTime );
                                // tv_leavereqtodate.setError("select correct date");
                            }

                        }else if (strdate.equals("4")){
                            if (date1.compareTo(date2) < 0)
                            {
                                tv_advancehotelfromdate.setText(strtodate );
                                // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                            }
                            else {
                                tv_advancehotelfromdate.setText(getCurrentDateTime );
                                // tv_leavereqtodate.setError("select correct date");
                            }

                        }else if (strdate.equals("5")){
                            if (date1.compareTo(date2) < 0)
                            {
                                tv_advancehotelviewtodate.setText(strtodate );
                                // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                            }
                            else {
                                tv_advancehotelviewtodate.setText(getCurrentDateTime );
                                // tv_leavereqtodate.setError("select correct date");
                            }
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void  advancefordialog(){
        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(AdvanceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.foradvancedialog);

        radioGroup_leavetype=dialog.findViewById(R.id.radioGroup_advancetype);

        radioGroup_leavetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Button btn=radioGroup_leavetype.findViewById(i);

                str=btn.getText().toString();
                tv_advancereqforadvance.setText(btn.getText().toString());

            }
        });
        tv_leavereqsubmit=dialog.findViewById(R.id.tv_advanceforsubmit);

        tv_leavereqsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (str.equals("temp")){
                    Toast.makeText(AdvanceActivity.this, "Please select Request type", Toast.LENGTH_SHORT).show();
                }else {
                        if (str.equals("Hotel")){
                            ly_advanceforhotelviewview.setVisibility(View.VISIBLE);
                            ly_advancefortravelview.setVisibility(View.GONE);
                          //  ly_advancetypeother.setVisibility(View.GONE);
                           // ly_advanceamount.setVisibility(View.VISIBLE);
                        }else if (str.equals("Travel")){
                          //  ly_advanceamount.setVisibility(View.VISIBLE);
                            ly_advanceforhotelviewview.setVisibility(View.GONE);
                          //  ly_advancetypeother.setVisibility(View.GONE);
                            ly_advancefortravelview.setVisibility(View.VISIBLE);
                        }else if (str.equals("Others")){
                          //  ly_advanceamount.setVisibility(View.VISIBLE);
                            ly_advancefortravelview.setVisibility(View.GONE);
                           // ly_advancetypeother.setVisibility(View.VISIBLE);
                            ly_advanceforhotelviewview.setVisibility(View.GONE);
                        }
                        dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void  advancetraveltypedialog(){

        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(AdvanceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.advancetraveltypedialog);

        radioGroup_leavetype=dialog.findViewById(R.id.radioGroup_advancetraveltype);

        radioGroup_leavetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Button btn=radioGroup_leavetype.findViewById(i);

                strtravel=btn.getText().toString();
                tv_advancetraveltrancporttype.setText(btn.getText().toString());

            }
        });
        tv_leavereqsubmit=dialog.findViewById(R.id.tv_advancefortravelsubmit);

        tv_leavereqsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (strtravel.equals("temp")){
                    Toast.makeText(AdvanceActivity.this, "Please select Request type", Toast.LENGTH_SHORT).show();
                }else {
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void advanceforlistdialog(){

        ListView li_trainigcourselist;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(AdvanceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.trainingtype_dialog);

        TextView claimtype=dialog.findViewById(R.id.claimtype);
        claimtype.setText("Advance For");

        final List<GetMasterInfo.ListAdvanceMaster_> listadvance= preferance.getInstance(AdvanceActivity.this).getadvancelist("advance");

        final AdvanceAdapter adapter = new AdvanceAdapter(AdvanceActivity.this, listadvance);

        li_trainigcourselist=dialog.findViewById(R.id.li_trainigcourselist);

        li_trainigcourselist.setAdapter(adapter);

        li_trainigcourselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tv_advancereqforadvance.setText(listadvance.get(position).getName());
                advanceId=listadvance.get(position).getAdvanceMasterId();
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public void saveAdvanced(String UserId, String Datee, String AdvanceMasterId,String FromDate,String ToDate,String Location,String Description,String Amount){

        final ProgressDialog progressDialog = new ProgressDialog(AdvanceActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        SaveAdvanceModel user = new SaveAdvanceModel(UserId,Datee,AdvanceMasterId,FromDate,ToDate,Location,Description,Amount);

        Call<SaveAdvanceModel> call1 = apiInterface.saveadvancerequest(user);
        call1.enqueue(new Callback<SaveAdvanceModel>() {
            @Override
            public void onResponse(Call<SaveAdvanceModel> call, Response<SaveAdvanceModel> response) {

                SaveAdvanceModel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                Toast.makeText(AdvanceActivity.this, str, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<SaveAdvanceModel> call, Throwable t) {
                call.cancel();
                String str= call.toString();
                faillerdiaolog(str);
                //   Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(AdvanceActivity.this);
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
