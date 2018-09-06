package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.Request.RequestMenu;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Profile.Profile;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

         LinearLayoutManager linearlayoutmanager;

        static String[] nameArray = {"Profile","Time & Attendance","Requst","Payroll","Notification","Task Monitering"};

        static Integer[] iconArray = {R.drawable.profile, R.drawable.timesheet_icon, R.drawable.request_icon,R.drawable.payroll_icon,R.drawable.notification_icon,R.drawable.task_manager_icon};

       ArrayList<Menuitemmodel> menuarraylist;

       RecyclerView recyclearview;
       private LinearLayout ly_homerequest,ly_timeAttendance,ly_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();
        ly_homerequest.setOnClickListener(this);
        ly_timeAttendance.setOnClickListener(this);
        ly_profile.setOnClickListener(this);

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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/
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
        ly_timeAttendance=findViewById(R.id.ly_timeAttendance);
        ly_profile=findViewById(R.id.ly_profile);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.ly_timeAttendance:
                intent=new Intent(HomeActivity.this, TimeAttendance_Menu.class);
                startActivity(intent);
                break;
            case R.id.ly_homerequest:
                intent = new Intent(HomeActivity.this, RequestMenu.class);
                startActivity(intent);
                break;
            case R.id.ly_profile:
                intent = new Intent(HomeActivity.this, Profile.class);
                startActivity(intent);
                break;
        }
    }
}
