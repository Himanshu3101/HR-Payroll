package com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request.ViewPager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

//import com.yoeki.iace.societymanagment.Home_Page;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Approval_Request.Requests;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;
import com.yoeki.kalpnay.hrporatal.setting.Edittextclass;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RequestManagementTab extends AppCompatActivity {

    private TabLayout RN_tabLayout;
    private ViewPager RN_viewPager;
    Button RN_bck;
    Toolbar toolbar;
    int whichdate=0;
    AppCompatButton request_filter;
    String Com_fromDate, Com_toDate;
    private int mYear, mMonth, mDay;
    Edittextclass req_filter_fromdate,req_filter_todate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_management_tab);

        RN_bck = (Button) findViewById(R.id.req_back);
        request_filter = (AppCompatButton)findViewById(R.id.request_filter);
        RN_viewPager = (ViewPager) findViewById(R.id.rn_pager);

        setupViewPager(RN_viewPager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RN_tabLayout = (TabLayout) findViewById(R.id.rn_tabLayout);
//        RN_tabLayout.addTab(RN_tabLayout.newTab().setText("Request"));
//        RN_tabLayout.addTab(RN_tabLayout.newTab().setText("Request Closed"));

        RN_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        RN_tabLayout.setupWithViewPager(RN_viewPager);

        RN_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeAttendance_Menu.class);
                startActivity(intent);
                finish();
            }
        });

        request_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterRequest();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllRequest(), "All");
        adapter.addFragment(new ApprovedRequest(), "Approved");
        adapter.addFragment(new DeclineRequest(), "Decline");
        adapter.addFragment(new PendingRequest(), "Pending");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), TimeAttendance_Menu.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void filterRequest(){

        AppCompatButton req_filterFrom_date,req_filterTo_date;
        Textclass saveFilter;
        Spinner srch_req_by_type;
        ArrayAdapter<String> reqt_lst_Name;


        final Dialog dialog = new Dialog(RequestManagementTab.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.request_filter);
        dialog.setCanceledOnTouchOutside(true);
        req_filterFrom_date = dialog.findViewById(R.id.req_filterFrom_date);
        req_filterTo_date = dialog.findViewById(R.id.req_filterTo_date);
        req_filter_fromdate = dialog.findViewById(R.id.req_filter_fromdate);
        req_filter_todate = dialog.findViewById(R.id.req_filter_todate);
        srch_req_by_type = dialog.findViewById(R.id.srch_req_by_type);
        saveFilter = dialog.findViewById(R.id.saveFilter);

        req_filterFrom_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        req_filterTo_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichdate=1;
                datePicker();
            }
        });

        saveFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Requests.class);
                startActivity(intent);
                finish();
            }
        });

        final ArrayList RequestListArray = new ArrayList<>();
        RequestListArray.add("Sick Leave");
        RequestListArray.add("Annual Leave");
        RequestListArray.add("Emergency Leave");
        RequestListArray.add("Casual Leave");
        RequestListArray.add("Medical Leave");
        RequestListArray.add("Paid Leave");
        RequestListArray.add("Un-Paid Leave");
        RequestListArray.add("Materanity Leave");

        reqt_lst_Name = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, RequestListArray);
        reqt_lst_Name.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        srch_req_by_type.setAdapter(reqt_lst_Name);
        reqt_lst_Name.insert("Search by Type", 0);

        srch_req_by_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String client_Selection = adapterView.getItemAtPosition(position).toString();
                String check_client_nme = "--Select Request Type--";

                if(!client_Selection.equals("--Search by Type--")){

                    for (int i = 0; i < RequestListArray.size(); i++) {

                        String listName = String.valueOf(RequestListArray.get(i));

//                        if (listName.equals(client_Selection)) {
//                            try{
////                                requesttype.setText(listName);
//                                Requestids = String.valueOf(db.getReqListID(listName));
//                                S=1;
//
//                            }catch(Exception e){
//                                e.printStackTrace();
//                            }
//                        }
                    }
//                    datafrrequest();
                }else{
//                    requesttype.setText("--Search by Type--");
//                    datafrrequest();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dialog.show();
    }

    private void datePicker(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date_time = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                        //*************Call Time Picker Here ********************
                        if (whichdate == 1) {
                            Com_toDate = date_time;
                            req_filter_todate.setText(date_time);
                            whichdate=0;
                        }else{
                            Com_fromDate = date_time;
                            req_filter_fromdate.setText(date_time);
                            whichdate=0;
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}