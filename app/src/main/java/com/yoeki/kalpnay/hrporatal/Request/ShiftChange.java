package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShiftChange extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton img_backreqshiftchange;
    private TextView tv_shiftchangedate,tv_shiftchangetodep;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_change);

        initialize();

        img_backreqshiftchange.setOnClickListener(this);
        tv_shiftchangedate.setOnClickListener(this);
        tv_shiftchangetodep.setOnClickListener(this);
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
                tochangedepartmentdialog();
                break;
        }
    }

    public void initialize() {

        img_backreqshiftchange = findViewById(R.id.img_backreqshiftchange);
        tv_shiftchangedate = findViewById(R.id.tv_shiftchangedate);
        tv_shiftchangetodep=findViewById(R.id.tv_shiftchangetodep);
    }

    public void shiftdate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(ShiftChange.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_shiftchangedate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        String strtodate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

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
                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                        } else {
                            tv_shiftchangedate.setText(getCurrentDateTime);
                            // tv_leavereqtodate.setError("select correct date");
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void tochangedepartmentdialog() {

        final ArrayList<TochangeModel> arraylistdep=new ArrayList<>();
        TochangeModel data1=new TochangeModel();
        data1.setDepartmentname("Hr");
        arraylistdep.add(data1);

        TochangeModel data2=new TochangeModel();
        data2.setDepartmentname("Technical");
        arraylistdep.add(data2);
        TochangeModel data3=new TochangeModel();
        data3.setDepartmentname("Network");
        arraylistdep.add(data3);

        TochangeModel data4=new TochangeModel();
        data4.setDepartmentname("sales");
        arraylistdep.add(data4);
        TochangeModel data5=new TochangeModel();
        data5.setDepartmentname("Markiting");
        arraylistdep.add(data5);

        final TochangeAdapter adapter = new TochangeAdapter(ShiftChange.this, arraylistdep);

        final Dialog dialog = new Dialog(ShiftChange.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.tochangeshitdialog);

        ListView listsponser = dialog.findViewById(R.id.li_tochangelist);

        listsponser.setAdapter(adapter);

        listsponser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tv_shiftchangetodep.setText(arraylistdep.get(i).getDepartmentname());
                dialog.dismiss();

            }
        });

        dialog.show();
    }
}