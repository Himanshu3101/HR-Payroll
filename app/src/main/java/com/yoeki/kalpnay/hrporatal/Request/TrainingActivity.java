package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener {

    String str="temp";
    ImageView img_backtrainingrequest;
    TextView tv_trainingreqdate,tv_trainingreqtype;
    EditText edt_trainingreqtitle;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        initialize();


        img_backtrainingrequest.setOnClickListener(this);
        tv_trainingreqdate.setOnClickListener(this);
        edt_trainingreqtitle.setOnClickListener(this);
        tv_trainingreqtype.setOnClickListener(this);
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
        }
    }

    public  void initialize(){

        img_backtrainingrequest=findViewById(R.id.img_backtrainingrequest);
        tv_trainingreqdate=findViewById(R.id.tv_trainingreqdate);

        edt_trainingreqtitle=findViewById(R.id.edt_trainingreqtitle);
        tv_trainingreqtype=findViewById(R.id.tv_trainingreqtype);
    }

    public void trainingtypedialog(){
        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(TrainingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.trainingtype_dialog);

        radioGroup_leavetype=dialog.findViewById(R.id.radioGroup_trainingtype);

        radioGroup_leavetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Button btn=radioGroup_leavetype.findViewById(i);

                str=btn.getText().toString();
                tv_trainingreqtype.setText(btn.getText().toString());

            }
        });
        tv_leavereqsubmit=dialog.findViewById(R.id.tv_trainingsubmit);
        tv_leavereqsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (str.equals("temp")){
                    Toast.makeText(TrainingActivity.this, "Please select Training type", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.dismiss();
                }
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
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(TrainingActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_trainingreqdate.setText(year + "-" + (monthOfYear + 1) +"-"+dayOfMonth );

                        String strtodate=year + "-" + (monthOfYear + 1) +"-"+dayOfMonth;

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
}
