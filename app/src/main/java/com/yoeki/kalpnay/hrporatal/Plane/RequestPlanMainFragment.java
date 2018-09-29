package com.yoeki.kalpnay.hrporatal.Plane;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.R;

import java.util.ArrayList;
import java.util.List;

public class RequestPlanMainFragment extends Fragment {

    private TabLayout tabs_requestplan;
    private ViewPager viewpager_requstplan;

    public static RequestPlanMainFragment newInstance() {
        RequestPlanMainFragment fragment = new RequestPlanMainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requestplanmain, container, false);

        tabs_requestplan=view.findViewById(R.id.tabs_requestplan);
        viewpager_requstplan=view.findViewById(R.id.viewpager_requstplan);

        setupViewPager(viewpager_requstplan);
        tabs_requestplan.setupWithViewPager(viewpager_requstplan);
        return view;

       }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new RequestFragment(), "All");
        adapter.addFragment(new ApprovedRequestFragment(), "Approved ");
        adapter.addFragment(new PlanchangerequestFragment(), "Change");
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

