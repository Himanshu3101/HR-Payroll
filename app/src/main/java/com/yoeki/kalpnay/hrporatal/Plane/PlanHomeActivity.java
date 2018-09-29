package com.yoeki.kalpnay.hrporatal.Plane;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.TextclassTextFrHeader;

public class PlanHomeActivity extends AppCompatActivity implements View.OnClickListener{
    TextclassTextFrHeader notifi_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_home);

         notifi_header = (TextclassTextFrHeader)findViewById(R.id.notifi_header);
          BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_plan);
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

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layoutplan, AllplanFragment.newInstance());
        transaction.commit();

    }
    @Override
    public void onClick(View v) {

    }
}
