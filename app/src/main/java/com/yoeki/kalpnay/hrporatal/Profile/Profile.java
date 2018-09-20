package com.yoeki.kalpnay.hrporatal.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;
import com.yoeki.kalpnay.hrporatal.Profile.BankDetails.BankAccountDetails;
import com.yoeki.kalpnay.hrporatal.Profile.Certification.Certification;
import com.yoeki.kalpnay.hrporatal.Profile.Dependent.Dependent;
import com.yoeki.kalpnay.hrporatal.Profile.Qualification.Qualification;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.TextclassTextFrHeader;

import java.lang.reflect.Field;

public class Profile extends AppCompatActivity {
    ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    TextclassTextFrHeader headerName;
    Button Pro_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profie);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        headerName = (TextclassTextFrHeader) findViewById(R.id.profileHeader);
        Pro_back = (Button)findViewById(R.id.Pro_back);
        headerName.setText("Profile");
        setViewPager();
        disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile:
                                headerName.setText("Profile");
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.bank:
                                headerName.setText("Bank Details");
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.qualification:
                                headerName.setText("Education");
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.certification:
                                headerName.setText("Certification");
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.dependent:
                                headerName.setText("Dependent");
                                viewPager.setCurrentItem(4);
                                break;
                        }

                        return true;
                    }
                });

        Pro_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void setViewPager() {
        viewPager = findViewById(R.id.frame_layout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Personal_Info());
        viewPagerAdapter.addFragment(new BankAccountDetails());
        viewPagerAdapter.addFragment(new Qualification());
        viewPagerAdapter.addFragment(new Certification());
        viewPagerAdapter.addFragment(new Dependent());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    headerName.setText("Profile");
                }if(position==1){
                    headerName.setText("Bank Details");
                }if(position==2){
                    headerName.setText("Qualification");
                }if(position==3){
                    headerName.setText("Certification");
                }if(position==4){
                    headerName.setText("Dependent");
                }
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi

                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

}
