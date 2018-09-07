package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import android.widget.LinearLayout;

import com.yoeki.kalpnay.hrporatal.Profile.Profile;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.Request.RequestMenu;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

         LinearLayoutManager linearlayoutmanager;

        static String[] nameArray = {"Profile","Time Sheet","Requst","Payroll","Notification","Task Monitering"};

        static Integer[] iconArray = {R.drawable.profile, R.drawable.timesheet_icon, R.drawable.request_icon, R.drawable.payroll_icon, R.drawable.notification_icon, R.drawable.task_manager_icon};

       ArrayList<Menuitemmodel> menuarraylist;

       RecyclerView recyclearview;
       private LinearLayout ly_homerequest,ly_profile,ly_timeAttendance;
      private static final int MY_PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            checkPermission();
        }

        ly_homerequest.setOnClickListener(this);
        ly_profile.setOnClickListener(this);
        ly_timeAttendance.setOnClickListener(this);

        menuarraylist=new ArrayList<>();
        linearlayoutmanager=new LinearLayoutManager(this);
      //  recyclearview=findViewById(R.id.reyc_mainmenuicon);

       // recyclearview.setHasFixedSize(true);

       // linearlayoutmanager = new LinearLayoutManager(this);
       // recyclearview.setLayoutManager(new GridLayoutManager(this, 2));
       // recyclearview.setItemAnimator(new DefaultItemAnimator());
        ;
       /* for (int i=0;i<nameArray.length;i++){

            menuarraylist.add(new Menuitemmodel(
                    iconArray[i],
                    nameArray[i]
            ));
        }*/
/*
        Mainmenuadapter adapter=new Mainmenuadapter(HomeActivity.this,menuarraylist);
        recyclearview.setAdapter(adapter);*/
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        dialog.show();
    }

    public  void initialize(){
        ly_homerequest=findViewById(R.id.ly_homerequest);
        ly_profile=findViewById(R.id.ly_profile);
        ly_timeAttendance=findViewById(R.id.ly_timeAttendance);
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

}
