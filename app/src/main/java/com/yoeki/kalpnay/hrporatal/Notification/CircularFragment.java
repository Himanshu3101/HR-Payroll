package com.yoeki.kalpnay.hrporatal.Notification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CircularFragment extends Fragment {

    private RecyclerView ryc_circular;
    List<CircularModel.ListCircular> arraycircularlist;
    ApiInterface apiInterface;
    public static CircularFragment newInstance() {
        CircularFragment fragment = new CircularFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circular, container, false);

        ryc_circular=view.findViewById(R.id.ryc_circular);

        arraycircularlist=new ArrayList<>();
        String  user_id=null;
        user_id = preferance.getInstance(getActivity()).getUserId();

        findcircular(user_id);
        return view;
    }

    public  void findcircular(String UserId){

        arraycircularlist.clear();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        CircularModel user = new CircularModel(UserId);

        Call<CircularModel> call1 = apiInterface.notificationcircular(user);
        call1.enqueue(new Callback<CircularModel>() {
            @Override
            public void onResponse(Call<CircularModel> call, Response<CircularModel> response) {

                CircularModel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                arraycircularlist=user1.getListCircular();

                if (status.equals("Success")){
                    ryc_circular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    // rec_leavereqattachment.setLayoutManager(new Hori);
                    ryc_circular.setItemAnimator(new DefaultItemAnimator());

                    CircularAdapter adapter=new CircularAdapter(getActivity(),arraycircularlist);
                    ryc_circular.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CircularModel> call, Throwable t) {
                call.cancel();
                Toast.makeText(getActivity(), "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
