package com.yoeki.kalpnay.hrporatal.Plane;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.Request.TochangeAdapter;
import com.yoeki.kalpnay.hrporatal.Request.TochangeModel;

import java.util.ArrayList;

public class PlanformActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_wherelocation,tv_location,tv_locationgeneral,tv_employeename;
    LinearLayout ly_location,ly_locationgeneral;
    String str;
    FrameLayout frm_formback;
    ImageView img_backform;
    Button time_planformsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planform);

        initialize();

        img_backform.setOnClickListener(this);
        time_planformsubmit.setOnClickListener(this);
        tv_wherelocation.setOnClickListener(this);
        tv_location.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_wherelocation:
                locationdialog();
                break;
            case R.id.img_backform:
                finish();
                break;
            case R.id.time_planformsubmit:
                success();
                break;
            case  R.id.tv_location:
                locationformdialog();
                break;
        }
    }

    public  void initialize(){

        tv_wherelocation=findViewById(R.id.tv_wherelocation);
        img_backform=findViewById(R.id.img_backform);
        time_planformsubmit=findViewById(R.id.time_planformsubmit);
        tv_location=findViewById(R.id.tv_location);
        tv_locationgeneral=findViewById(R.id.tv_locationgeneral);
        ly_locationgeneral=findViewById(R.id.ly_location);
        ly_location=findViewById(R.id.ly_locationgeneral);
    }
    public void locationdialog(){
        final RadioGroup radioGroup_locationtype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(PlanformActivity.this);
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

        final Dialog dialog = new Dialog(PlanformActivity.this);
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

        final ArrayList<TochangeModel> arraylistdep=new ArrayList<>();
        TochangeModel data1=new TochangeModel();
        data1.setDepartmentname("E-210,Noida,Sec-63");
        arraylistdep.add(data1);

        TochangeModel data2=new TochangeModel();
        data2.setDepartmentname("h-25,Sec-3,Noida");
        arraylistdep.add(data2);

        TochangeModel data3=new TochangeModel();
        data3.setDepartmentname("A-45,Sec-2,Noida");
        arraylistdep.add(data3);

        TochangeModel data4=new TochangeModel();
        data4.setDepartmentname("H-119,Sec-62,Noida");
        arraylistdep.add(data4);

        TochangeModel data5=new TochangeModel();
        data5.setDepartmentname("G-19,Sec-63,Noida");
        arraylistdep.add(data5);

        TochangeModel data6=new TochangeModel();
        data6.setDepartmentname("B-225,New Ashok Nagar,New Delhi");
        arraylistdep.add(data6);

        TochangeModel data7=new TochangeModel();
        data7.setDepartmentname("B-19,Sec-19,Noida");
        arraylistdep.add(data7);

        final TochangeAdapter adapter = new TochangeAdapter(PlanformActivity.this, arraylistdep);

        final Dialog dialog = new Dialog(PlanformActivity.this);
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
}
