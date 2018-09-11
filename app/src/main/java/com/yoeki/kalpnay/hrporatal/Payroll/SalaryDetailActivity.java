package com.yoeki.kalpnay.hrporatal.Payroll;

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

public class SalaryDetailActivity extends AppCompatActivity implements View.OnClickListener {
     private TabLayout tabs_salary;
     private ViewPager viewpager_salary;
     private ImageView img_salarydetailback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_detail);

        initialize();

        setupViewPager(viewpager_salary);
        tabs_salary.setupWithViewPager(viewpager_salary);
       // tabs_salary.
       // tabs_salary.setOnTabSelectedListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_salarydetailback:
                finish();
                break;
        }
    }
    public void initialize(){

        tabs_salary=(TabLayout) findViewById(R.id.tabs_salary);
        viewpager_salary=findViewById(R.id.viewpager_salary);
        img_salarydetailback=findViewById(R.id.img_salarydetailback);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SalarypaystructureFragment(), "Pay Structure");
        adapter.addFragment(new ViewSalarystructureFragment(), "View Payshilp");
        adapter.addFragment(new SalaryEndsFragment(), "Ends");
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
}
