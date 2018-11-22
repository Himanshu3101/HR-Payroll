package com.yoeki.kalpnay.hrporatal.Plane;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionLayout;
import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllplanFragment extends Fragment {

    private RecyclerView ryc_plan;
    List<Allplanmanagermodule.ListEmpPlan> arraycircularlist;
    FloatingActionLayout fab;
    ApiInterface apiInterface;

    public static AllplanFragment newInstance() {
        AllplanFragment fragment = new AllplanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_plane, container, false);

        ryc_plan=view.findViewById(R.id.ryc_plan);

         fab =view.findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),PlanformActivity.class);
                        getActivity().startActivity(intent);
            }
         });

        arraycircularlist=new ArrayList<>();

        String  user_id=null;
        user_id = preferance.getInstance(getActivity()).getUserId();

        allplanmanagerrequest(user_id);

        return view;

        }

    public  void allplanmanagerrequest(String UserId){

        arraycircularlist.clear();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        Allplanmanagermodule user = new Allplanmanagermodule(UserId);

        Call<Allplanmanagermodule> call1 = apiInterface.allplanmanager(user);
        call1.enqueue(new Callback<Allplanmanagermodule>() {
            @Override
            public void onResponse(Call<Allplanmanagermodule> call, Response<Allplanmanagermodule> response) {

                Allplanmanagermodule user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                arraycircularlist=user1.getListEmpPlan();

                if (status.equals("Success")){
                    ryc_plan.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    // rec_leavereqattachment.setLayoutManager(new Hori);
                    ryc_plan.setItemAnimator(new DefaultItemAnimator());

                    AllplanrequestAdapter adapter=new AllplanrequestAdapter(getActivity(),arraycircularlist);
                    ryc_plan.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Allplanmanagermodule> call, Throwable t) {
                call.cancel();
                Toast.makeText(getActivity(), "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    }
