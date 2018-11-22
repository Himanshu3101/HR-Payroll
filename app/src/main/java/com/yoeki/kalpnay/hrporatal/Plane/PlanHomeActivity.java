package com.yoeki.kalpnay.hrporatal.Plane;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.TextclassTextFrHeader;

public class PlanHomeActivity extends AppCompatActivity implements View.OnClickListener{
    TextclassTextFrHeader notifi_header;
    AppCompatButton img_backplan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_home);

         notifi_header = (TextclassTextFrHeader)findViewById(R.id.notifi_header);
          BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_plan);
        img_backplan = (AppCompatButton) findViewById(R.id.img_backplan);
            notifi_header.setText("All Plan");

            bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_allplan:
                                selectedFragment = AllplanFragment.newInstance();
                                notifi_header.setText("All Plan");
                                break;
                            case R.id.action_requestplan:
                                selectedFragment = RequestPlanMainFragment.newInstance();
                                notifi_header.setText("Requested Plan");
                                break;
                        }

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layoutplan, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });


        img_backplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layoutplan, AllplanFragment.newInstance());
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
