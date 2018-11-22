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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Edittextclass;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener {

    String str="temp",trainingcorseId;
    AppCompatButton img_backtrainingrequest;
    TextView tv_trainingreqdate,tv_trainingreqtype;
    Edittextclass edt_trainingreqtitle,edt_trainingprovider,edt_trainingtotaldays,edt_trainingcost;
    private int mYear, mMonth, mDay;
    AppCompatButton tv_trainingreqsubmit;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initialize();

        img_backtrainingrequest.setOnClickListener(this);
        tv_trainingreqdate.setOnClickListener(this);
        edt_trainingreqtitle.setOnClickListener(this);
        tv_trainingreqtype.setOnClickListener(this);
        tv_trainingreqsubmit.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.tv_trainingreqtype:
                trainingtypedialog();
                break;

            case R.id.tv_trainingreqdate:
                trainigdate();
                break;
            case R.id.img_backtrainingrequest:
                finish();
                break;

            case R.id.tv_trainingreqsubmit:

                String  user_id=null;
                user_id = preferance.getInstance(getApplicationContext()).getUserId();
                String strdate=tv_trainingreqdate.getText().toString();
                String strtitle=edt_trainingreqtitle.getText().toString();
               // String strservicetype=tv_trainingreqtype.getText().toString();
                String strserviceprovider=edt_trainingprovider.getText().toString();
                String strtotaldays=edt_trainingtotaldays.getText().toString();
                String strcost=edt_trainingcost.getText().toString();

                saveTraining(user_id,strdate,strtitle,trainingcorseId,strserviceprovider,strtotaldays,strcost);

                break;
        }
    }

    public  void initialize(){

        img_backtrainingrequest=findViewById(R.id.img_backtrainingrequest);
        tv_trainingreqdate=findViewById(R.id.tv_trainingreqdate);
        edt_trainingreqtitle=findViewById(R.id.edt_trainingreqtitle);
        tv_trainingreqtype=findViewById(R.id.tv_trainingreqtype);
        edt_trainingprovider=findViewById(R.id.edt_trainingprovider);
        edt_trainingtotaldays=findViewById(R.id.edt_trainingtotaldays);
        edt_trainingcost=findViewById(R.id.edt_trainingcost);

        tv_trainingreqsubmit=findViewById(R.id.tv_trainingreqsubmit);

    }

    public void trainingtypedialog(){

         ListView li_trainigcourselist;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(TrainingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.trainingtype_dialog);

        final List<GetMasterInfo.ListTraningServiceType>listtraining= preferance.getInstance(TrainingActivity.this).gettrainingcourseArrayList("trainingcourse");

        final TraininglistAdapter adapter = new TraininglistAdapter(TrainingActivity.this, listtraining);

        li_trainigcourselist=dialog.findViewById(R.id.li_trainigcourselist);

        li_trainigcourselist.setAdapter(adapter);

        li_trainigcourselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tv_trainingreqtype.setText(listtraining.get(position).getName());
                trainingcorseId=listtraining.get(position).getServiceTypeId();

                dialog.dismiss();

            }
        });

        dialog.show();

    }

    public  void trainigdate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(TrainingActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_trainingreqdate.setText(year + "/" + (monthOfYear + 1) +"/"+dayOfMonth );

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
                            tv_trainingreqdate.setText(strtodate );
                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                        }
                        else {
                            tv_trainingreqdate.setText(getCurrentDateTime );
                            // tv_leavereqtodate.setError("select correct date");
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    public void saveTraining(String UserId, String Datee, String Title, String ServiceTypeId, String ServiceProvider, String TotalDays, String Cost){

        final ProgressDialog progressDialog = new ProgressDialog(TrainingActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        SaveTraningRequest user = new SaveTraningRequest(UserId,Datee,Title,ServiceTypeId,ServiceProvider,TotalDays,Cost);

        Call<SaveTraningRequest> call1 = apiInterface.savetrainingrequest(user);
        call1.enqueue(new Callback<SaveTraningRequest>() {
            @Override
            public void onResponse(Call<SaveTraningRequest> call, Response<SaveTraningRequest> response) {

                SaveTraningRequest user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                Toast.makeText(TrainingActivity.this, str, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<SaveTraningRequest> call, Throwable t) {
                call.cancel();
                String str= call.toString();
                faillerdiaolog(str);
                //   Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(TrainingActivity.this);
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
