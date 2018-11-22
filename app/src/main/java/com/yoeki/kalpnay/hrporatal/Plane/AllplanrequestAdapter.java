package com.yoeki.kalpnay.hrporatal.Plane;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllplanrequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 2;
    private List<Allplanmanagermodule.ListEmpPlan> stringArrayList;
    private Activity activity;

    public AllplanrequestAdapter(Activity activity, List<Allplanmanagermodule.ListEmpPlan> strings){
        this.activity = activity;
        this.stringArrayList = strings;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflating recycle view item layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_plan, parent, false);
        return new ItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_circularname.setText(stringArrayList.get(position).getEmpName());
        itemViewHolder.tv_circularmsg.setText(stringArrayList.get(position).getLocation());
        itemViewHolder.tv_planedate.setText(stringArrayList.get(position).getCreatedOn());

        itemViewHolder.card_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String empId=stringArrayList.get(position).getEmpPlanId();
               String empname=stringArrayList.get(position).getEmpName();
               String empwhere=stringArrayList.get(position).getWheree();
               String emplocation=stringArrayList.get(position).getLocation();
               String emppurpose=stringArrayList.get(position).getPurpose();
                locationdialog(empId,empname,empwhere,emplocation,emppurpose);

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_circularname,tv_circularmsg,tv_planedate;
        CardView card_notification;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_circularname = itemView.findViewById(R.id.tv_planname);
            tv_circularmsg = itemView.findViewById(R.id.tv_planadress);
            card_notification=itemView.findViewById(R.id.card_plan);
            tv_planedate=itemView.findViewById(R.id.tv_planedate);
        }
    }

    public void locationdialog(final String empid, String empname, String empwhere, String emplocation, String emppurpose){
        final RadioGroup radioGroup_locationtype;
        TextView tv_changerequstmsg,tv_empname,tv_empwhere,tv_emplocation,tv_emppurpose;
        CardView card_approved;
        final Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_requestapprov);

        tv_changerequstmsg=dialog.findViewById(R.id.tv_changerequstmsg);
        card_approved=dialog.findViewById(R.id.card_approved);

        tv_empname=dialog.findViewById(R.id.tv_empname);
        tv_empwhere=dialog.findViewById(R.id.tv_empwhere);
        tv_emplocation=dialog.findViewById(R.id.tv_emplocation);
        tv_emppurpose=dialog.findViewById(R.id.tv_emppurpose);

        tv_empname.setText(empname);

        if (empwhere.equals("C")){
            tv_empwhere.setText("Company");
        }else if (empwhere.equals("V")){
            tv_empwhere.setText("Vendor");
        }else if (empwhere.equals("G")){
            tv_empwhere.setText("General");
        }

        tv_emplocation.setText(emplocation);
        tv_emppurpose.setText(emppurpose);

        card_approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  user_id=null;
                user_id = preferance.getInstance(activity).getUserId();
                approverequest(user_id,"A",empid);
                dialog.dismiss();

            }
        });

        tv_changerequstmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(activity,PlanformActivity.class);
                activity.startActivity(intent);
                dialog.dismiss();

            }
        });

        dialog.show();

    }

    public  void approverequest(String UserId,String status,String emp){

        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        ApiInterface apiInterface;  apiInterface= Api.getClient().create(ApiInterface.class);
        Approverequestmodel user = new Approverequestmodel(UserId,status,emp);
        Call<Approverequestmodel> call1 = apiInterface.approverequestmodel(user);
        call1.enqueue(new Callback<Approverequestmodel>() {
            @Override
            public void onResponse(Call<Approverequestmodel> call, Response<Approverequestmodel> response) {

                Approverequestmodel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Approverequestmodel> call, Throwable t) {
                call.cancel();
                Toast.makeText(activity, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}
