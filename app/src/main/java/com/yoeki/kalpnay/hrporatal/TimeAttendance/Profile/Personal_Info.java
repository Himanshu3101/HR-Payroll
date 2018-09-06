package com.yoeki.kalpnay.hrporatal.TimeAttendance.Profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yoeki.kalpnay.hrporatal.R;

public class Personal_Info  extends Fragment {
    public static Personal_Info newInstance() {
        Personal_Info fragment = new Personal_Info();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal__info, container, false);
    }
}
