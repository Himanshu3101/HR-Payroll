package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GreveanceActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_backrequestgreveance;
    private TextView tv_greveancedate,edt_greveancepersonname;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greveance);
             initialize();

        img_backrequestgreveance.setOnClickListener(this);
        tv_greveancedate.setOnClickListener(this);
        edt_greveancepersonname.setOnClickListener(this);
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
        }
    }

    public void initialize(){
        img_backrequestgreveance=findViewById(R.id.img_backrequestgreveance);
        tv_greveancedate=findViewById(R.id.tv_greveancedate);
        edt_greveancepersonname=findViewById(R.id.edt_greveancepersonname);
    }

    public  void greveancedate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(GreveanceActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_greveancedate.setText(year + "-" + (monthOfYear + 1) +"-"+dayOfMonth );

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

        final ArrayList<TochangeModel> arraylistdep=new ArrayList<>();
        TochangeModel data1=new TochangeModel();
        data1.setDepartmentname("Amarjeet");
        arraylistdep.add(data1);
        TochangeModel data2=new TochangeModel();
        data2.setDepartmentname("Mohit Kumar");
        arraylistdep.add(data2);
        TochangeModel data3=new TochangeModel();
        data3.setDepartmentname("Himanshu");
        arraylistdep.add(data3);

        TochangeModel data4=new TochangeModel();
        data4.setDepartmentname("Vertika");
        arraylistdep.add(data4);
        TochangeModel data5=new TochangeModel();
        data5.setDepartmentname("Mahindra");
        arraylistdep.add(data5);

        TochangeModel data6=new TochangeModel();
        data6.setDepartmentname("Archna");
        arraylistdep.add(data6);
        TochangeModel data7=new TochangeModel();
        data7.setDepartmentname("Abhishek");
        arraylistdep.add(data7);

        final TochangeAdapter adapter = new TochangeAdapter(GreveanceActivity.this, arraylistdep);

        final Dialog dialog = new Dialog(GreveanceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.involvedperson_dialog);

        ListView listsponser = dialog.findViewById(R.id.li_involvedlist);

        listsponser.setAdapter(adapter);

        listsponser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                edt_greveancepersonname.setText(arraylistdep.get(i).getDepartmentname());
                dialog.dismiss();

            }
        });
        dialog.show();
    }
}
