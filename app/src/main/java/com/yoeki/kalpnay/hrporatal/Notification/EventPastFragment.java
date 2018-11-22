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

public class EventPastFragment extends Fragment {


    RecyclerView ryc_pastevent;
    List<NotificationEventModel.ListSearchCircular> arraycircularlist;
    ApiInterface apiInterface;
    public static EventPastFragment newInstance() {
        EventPastFragment fragment = new EventPastFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pastevent, container, false);

        ryc_pastevent=view.findViewById(R.id.ryc_pastevent);
        arraycircularlist=new ArrayList<>();

        String  user_id=null;
        user_id = preferance.getInstance(getActivity()).getUserId();

        pastnotification(user_id,"P");

        return view;
    }

    public  void pastnotification(String UserId,String flag){

        arraycircularlist.clear();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        NotificationEventModel user = new NotificationEventModel(UserId,flag);

        Call<NotificationEventModel> call1 = apiInterface.notificationevent(user);
        call1.enqueue(new Callback<NotificationEventModel>() {
            @Override
            public void onResponse(Call<NotificationEventModel> call, Response<NotificationEventModel> response) {

                NotificationEventModel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                arraycircularlist=user1.getListSearchCircular();

                if (status.equals("Success")){
                    ryc_pastevent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    // rec_leavereqattachment.setLayoutManager(new Hori);
                    ryc_pastevent.setItemAnimator(new DefaultItemAnimator());

                    EventAdapter adapter=new EventAdapter(getActivity(),arraycircularlist);
                    ryc_pastevent.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<NotificationEventModel> call, Throwable t) {
                call.cancel();
                Toast.makeText(getActivity(), "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }


}
