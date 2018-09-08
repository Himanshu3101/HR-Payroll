package com.yoeki.kalpnay.hrporatal.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.yoeki.kalpnay.hrporatal.Profile.BankDetails.BankAccountDetails;
import com.yoeki.kalpnay.hrporatal.R;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profie);



    BottomNavigationView bottomNavigationView = (BottomNavigationView)
            findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.action_item1:
                    selectedFragment = Personal_Info.newInstance();
                    break;
                case R.id.action_item2:
                    selectedFragment = BankAccountDetails.newInstance();
                    break;
                case R.id.action_item3:
                    selectedFragment = Qualification.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    });

    //Manually displaying the first fragment - one time only
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, Personal_Info.newInstance());
        transaction.commit();

    //Used to select an item programmatically
    //bottomNavigationView.getMenu().getItem(2).setChecked(true);
}

        }
