package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.Benefits.Home;
import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.Login.LoginActivity;
import com.yoeki.kalpnay.hrporatal.Notification.NotificationHomeActivity;
import com.yoeki.kalpnay.hrporatal.Payroll.SalaryDetailActivity;
import com.yoeki.kalpnay.hrporatal.Plane.PlanHomeActivity;
import com.yoeki.kalpnay.hrporatal.Profile.Profile;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.Request.RequestMenu;
import com.yoeki.kalpnay.hrporatal.Servay.ServayHomeActivity;
import com.yoeki.kalpnay.hrporatal.Task_Monitoring.taskMonitoring;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private FragmentManager fragmentManager;
    ApiInterface apiInterface;

    LinearLayoutManager linearlayoutmanager;
    static String[] nameArray = {"Profile","Time Sheet","Requst","Payroll","Notification","Task Monitering"};
    static Integer[] iconArray = {R.drawable.profile, R.drawable.timesheet_icon, R.drawable.request_icon, R.drawable.payroll_icon, R.drawable.notification_icon, R.drawable.task_manager_icon};
    ArrayList<Menuitemmodel> menuarraylist;
    RecyclerView recyclearview;
    private LinearLayout ly_homerequest,ly_profile,ly_timeAttendance,ly_payroll,ly_notification,benefits,ly_task,ly_survey;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

        fragmentManager=getSupportFragmentManager();
        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //  final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        }

        ly_homerequest.setOnClickListener(this);
        ly_profile.setOnClickListener(this);
        ly_timeAttendance.setOnClickListener(this);
        ly_payroll.setOnClickListener(this);
        ly_notification.setOnClickListener(this);
        benefits.setOnClickListener(this);
        ly_task.setOnClickListener(this);
        ly_survey.setOnClickListener(this);

        menuarraylist=new ArrayList<>();
        linearlayoutmanager=new LinearLayoutManager(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textHeadername=navigationView.getHeaderView(0).findViewById(R.id.tv_heardename);

        String  username= preferance.getInstance(getApplicationContext()).getUserName();
        textHeadername.setText(username);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            Intent intent=new Intent(HomeActivity.this,SearchEmployeActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_notification){
            Intent intent=new Intent(HomeActivity.this,NotificationHomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profile){
            // Handle the camera action
        } else if (id == R.id.nav_changepassword) {

            changepassworddialog();
        }  /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }*/else if (id == R.id.nav_logout) {

            logout();

        } /*else if (id == R.id.nav_send) {
        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void changepassworddialog() {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.changepassword_dialog);

        final EditText edt_oldpass=dialog.findViewById(R.id.edt_oldpass);
        final EditText edt_newpassword=dialog.findViewById(R.id.edt_newpassword);
        final EditText edt_confirmpassword=dialog.findViewById(R.id.edt_confirmpassword);
        TextView tv_submitchange=dialog.findViewById(R.id.tv_submitchange);

        tv_submitchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  user_id=null,oldpass,newpass,confirmpass;
                user_id = preferance.getInstance(getApplicationContext()).getUserId();

                oldpass=edt_oldpass.getText().toString();
                newpass=edt_newpassword.getText().toString();
                confirmpass=edt_confirmpassword.getText().toString();

                if (oldpass.equals("")){
                    edt_oldpass.setError("Fill Required field");
                }else if (newpass.equals("")){
                    edt_newpassword.setError("Fill Required field");
                }else if (!newpass.equals(confirmpass)){
                    Toast.makeText(HomeActivity.this, "Password Do not match", Toast.LENGTH_SHORT).show();
                }else{
                    final ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
                    progressDialog.setCancelable(false); // set cancelable to false
                    progressDialog.setMessage("Please Wait"); // set message
                    progressDialog.show(); // show progress dialog

                    apiInterface= Api.getClient().create(ApiInterface.class);
                    Changepassresponce user = new Changepassresponce(user_id,oldpass,newpass);

                    Call<Changepassresponce> call1 = apiInterface.changepass(user);
                    call1.enqueue(new Callback<Changepassresponce>() {
                        @Override
                        public void onResponse(Call<Changepassresponce> call, Response<Changepassresponce> response) {
                            Changepassresponce user1 = response.body();
                            progressDialog.dismiss();
                            String str=user1.getMessage();

                            String status=user1.getStatus();
                            if (status.equals("Success")){
                                Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                //(LoginActivity).faillerdiaolog(str);
                                Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Changepassresponce> call, Throwable t) {
                            call.cancel();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
        dialog.show();
    }

    public  void initialize(){

        ly_homerequest=findViewById(R.id.ly_homerequest);
        ly_profile=findViewById(R.id.ly_profile);
        ly_timeAttendance=findViewById(R.id.ly_timeAttendance);
        ly_payroll=findViewById(R.id.ly_payroll);
        ly_notification=findViewById(R.id.ly_notification);
        benefits=findViewById(R.id.benefits);
        ly_task=findViewById(R.id.ly_task);
        ly_survey=findViewById(R.id.survey);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ly_homerequest:

                Intent intent=new Intent(HomeActivity.this, RequestMenu.class);
                startActivity(intent);

                break;
            case R.id.ly_profile:
                Intent intent1=new Intent(HomeActivity.this, Profile.class);
                startActivity(intent1);
                break;

            case R.id.ly_timeAttendance:
                Intent intent0=new Intent(HomeActivity.this, TimeAttendance_Menu.class);
                startActivity(intent0);
                break;
            case R.id.ly_payroll:
                Intent intentpayroll=new Intent(HomeActivity.this, SalaryDetailActivity.class);
                startActivity(intentpayroll);
                break;
            case  R.id.ly_notification:
                Intent inten=new Intent(HomeActivity.this, PlanHomeActivity.class);
                startActivity(inten);
                break;
            case  R.id.benefits:
                Intent Binten=new Intent(HomeActivity.this, Home.class);
                startActivity(Binten);
                break;
            case  R.id.ly_task:
                Intent nten=new Intent(HomeActivity.this, taskMonitoring.class);
                startActivity(nten);
                break;
            case  R.id.survey:
                Intent ntn=new Intent(HomeActivity.this, ServayHomeActivity.class);
                startActivity(ntn);
                break;
        }
    }

    protected void checkPermission(){
        if(ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(
                HomeActivity.this, Manifest.permission.READ_CONTACTS)
                + ContextCompat.checkSelfPermission(
                HomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    HomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    HomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                // If we should give explanation of requested permissions

                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("Read Contacts and Write External" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                HomeActivity.this,
                                new String[]{
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });
                builder.setNeutralButton("Cancel",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        HomeActivity.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        }else {
            // Do something, when permissions are already granted
            // Toast.makeText(HomeActivity.this,"Permissions already granted",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CODE:{
                // When request is cancelled, the results array are empty
                if(
                        (grantResults.length >0) &&
                                (grantResults[0]
                                        + grantResults[1]
                                        == PackageManager.PERMISSION_GRANTED
                                )
                        ){
                    // Permissions are granted
                    // Toast.makeText(HomeActivity.this,"Permissions granted.",Toast.LENGTH_SHORT).show();
                }else {
                    // Permissions are denied
                    //  Toast.makeText(HomeActivity.this,"Permissions denied.",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    public  void logout(){
        new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be Logout!")
                .setConfirmText("Yes")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        preferance.getInstance(getApplicationContext()).clearuserSession();

                        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                        startActivity(intent);

                        finish();
                    }
                })
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}
