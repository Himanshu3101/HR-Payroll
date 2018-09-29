package com.yoeki.kalpnay.hrporatal.Plane;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.Request.TochangeAdapter;
import com.yoeki.kalpnay.hrporatal.Request.TochangeModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmployeeRequestformActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_backformemployee;
    TextView tv_wherelocation,tv_location,tv_locationgeneral;
    LinearLayout ly_location,ly_locationgeneral;
    TextView tv_employeeformdate;
    Button time_planformsubmit;
    String str;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_requestform);

        initialize();

        img_backformemployee.setOnClickListener(this);
        tv_employeeformdate.setOnClickListener(this);

        time_planformsubmit.setOnClickListener(this);
        tv_wherelocation.setOnClickListener(this);
        tv_location.setOnClickListener(this);
    }

    public void initialize(){
        img_backformemployee=findViewById(R.id.img_backformemployee);
        tv_employeeformdate=findViewById(R.id.tv_employeeformdate);

        tv_wherelocation=findViewById(R.id.tv_wherelocation);
        time_planformsubmit=findViewById(R.id.time_planformsubmit);
        tv_location=findViewById(R.id.tv_location);
        tv_locationgeneral=findViewById(R.id.tv_locationgeneral);
        ly_locationgeneral=findViewById(R.id.ly_location);
        ly_location=findViewById(R.id.ly_locationgeneral);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.img_backformemployee:
                finish();
                break;

            case R.id.tv_wherelocation:
                locationdialog();
                break;

            case R.id.time_planformsubmit:
                success();
                break;
            case  R.id.tv_location:
                locationformdialog();
                break;

            case R.id.tv_employeeformdate:
                formdate();
                break;

        }

    }

    public void locationdialog(){
        final RadioGroup radioGroup_locationtype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(EmployeeRequestformActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_wherelocation);

        radioGroup_locationtype=dialog.findViewById(R.id.radioGroup_locationtype);

        radioGroup_locationtype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Button btn=radioGroup_locationtype.findViewById(i);

                str=btn.getText().toString();
                tv_wherelocation.setText(btn.getText().toString());

                if (str.equals("Company Location")){
                    ly_location.setVisibility(View.GONE);
                    ly_locationgeneral.setVisibility(View.VISIBLE);
                }else if (str.equals("Vendor Location")){
                    ly_location.setVisibility(View.GONE);
                    ly_locationgeneral.setVisibility(View.VISIBLE);
                }else if (str.equals("General")){
                    ly_location.setVisibility(View.VISIBLE);
                    ly_locationgeneral.setVisibility(View.GONE);
                }
            }
        });

        tv_leavereqsubmit=dialog.findViewById(R.id.tv_leavereqsubmit);
        tv_leavereqsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public  void success(){

        final Dialog dialog = new Dialog(EmployeeRequestformActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_successform);

        TextView tv_submitformmsg=dialog.findViewById(R.id.tv_submitformmsg);
        tv_submitformmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    public void locationformdialog() {

        final ArrayList<TochangeModel> arraylistdep = new ArrayList<>();
        TochangeModel data1 = new TochangeModel();
        data1.setDepartmentname("E-210,Noida,Sec-63");
        arraylistdep.add(data1);

        TochangeModel data2 = new TochangeModel();
        data2.setDepartmentname("h-25,Sec-3,Noida");
        arraylistdep.add(data2);

        TochangeModel data3 = new TochangeModel();
        data3.setDepartmentname("A-45,Sec-2,Noida");
        arraylistdep.add(data3);

        TochangeModel data4 = new TochangeModel();
        data4.setDepartmentname("H-119,Sec-62,Noida");
        arraylistdep.add(data4);

        TochangeModel data5 = new TochangeModel();
        data5.setDepartmentname("G-19,Sec-63,Noida");
        arraylistdep.add(data5);

        TochangeModel data6 = new TochangeModel();
        data6.setDepartmentname("B-225,New Ashok Nagar,New Delhi");
        arraylistdep.add(data6);

        TochangeModel data7 = new TochangeModel();
        data7.setDepartmentname("B-19,Sec-19,Noida");
        arraylistdep.add(data7);

        final TochangeAdapter adapter = new TochangeAdapter(EmployeeRequestformActivity.this, arraylistdep);

        final Dialog dialog = new Dialog(EmployeeRequestformActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_location);

        ListView listsponser = dialog.findViewById(R.id.li_locationlist);

        listsponser.setAdapter(adapter);

        listsponser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tv_location.setText(arraylistdep.get(i).getDepartmentname());
                dialog.dismiss();

            }
        });
        dialog.show();
       }

    public  void formdate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(EmployeeRequestformActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_employeeformdate.setText(year + "-" + (monthOfYear + 1) +"-"+dayOfMonth );

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
                            tv_employeeformdate.setText(strtodate );
                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                        }
                        else {
                            tv_employeeformdate.setText(getCurrentDateTime );
                            //  tv_leavereqfromdate.setError("select correct date");
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
