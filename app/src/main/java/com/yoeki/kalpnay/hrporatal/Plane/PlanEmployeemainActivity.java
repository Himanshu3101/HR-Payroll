package com.yoeki.kalpnay.hrporatal.Plane;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;
import java.util.List;

public class PlanEmployeemainActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout tabs_requestplanemployee;
    ViewPager viewpager_requstplanemployee;
    ImageView img_backemployeerequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_employeemain);

        initialize();

        setupViewPager(viewpager_requstplanemployee);
        tabs_requestplanemployee.setupWithViewPager(viewpager_requstplanemployee);

        img_backemployeerequest.setOnClickListener(this);
    }

    public void initialize(){

        tabs_requestplanemployee=findViewById(R.id.tabs_requestplanemployee);
        viewpager_requstplanemployee=findViewById(R.id.viewpager_requstplanemployee);
        img_backemployeerequest=findViewById(R.id.img_backemployeerequest);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TodayEmployeeFragment(), "Today");
        adapter.addFragment(new PastEmployeeFragment(), "Past ");
        adapter.addFragment(new UpcomingEmployeeFragment(), "Upcomimg");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_backemployeerequest:
                finish();
                break;
        }
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
}
