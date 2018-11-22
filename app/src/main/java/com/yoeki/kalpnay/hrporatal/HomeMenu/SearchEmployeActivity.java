package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class SearchEmployeActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView ryc_search;
    List<SearchEmployeeModel.ListAllEmployee> employelist;
    private ImageView img_searchback,img_searchemp;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employe);

        employelist=new ArrayList<>();

        initialize();

        String  user_id=null;
        user_id = preferance.getInstance(getApplicationContext()).getUserId();

        searchEmployee(user_id,"","","");

        img_searchback.setOnClickListener(this);
        img_searchemp.setOnClickListener(this);
    }

    public void initialize(){
        ryc_search=findViewById(R.id.ryc_search);
        img_searchback=findViewById(R.id.img_searchback);
        img_searchemp=findViewById(R.id.img_searchemp);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_searchback:
                finish();
                break;

            case R.id.img_searchemp:
                searchempdiaolog("testing");
                break;
        }
    }

    public void searchEmployee(String UserId,String Name, String Designation, String Department ){

        employelist.clear();
        final ProgressDialog progressDialog = new ProgressDialog(SearchEmployeActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        SearchEmployeeModel user = new SearchEmployeeModel(UserId,Name,Designation,Department);

        Call<SearchEmployeeModel> call1 = apiInterface.searchemployee(user);
        call1.enqueue(new Callback<SearchEmployeeModel>() {
            @Override
            public void onResponse(Call<SearchEmployeeModel> call, Response<SearchEmployeeModel> response) {

                SearchEmployeeModel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                employelist=user1.getListAllEmployee();

                if (status.equals("Success")){
                    ryc_search.setLayoutManager(new LinearLayoutManager(SearchEmployeActivity.this, LinearLayoutManager.VERTICAL, false));
                    // rec_leavereqattachment.setLayoutManager(new Hori);
                    ryc_search.setItemAnimator(new DefaultItemAnimator());
                    SearchAdapter adapter=new SearchAdapter(SearchEmployeActivity.this,employelist);
                    ryc_search.setAdapter(adapter);
                }else {
                    Toast.makeText(SearchEmployeActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchEmployeeModel> call, Throwable t){
                call.cancel();
                String str= call.toString();
                faillerdiaolog(str);
                //Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(SearchEmployeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.failuremsg);
        TextView tv_failmsg=dialog.findViewById(R.id.tv_failmsg);
        tv_failmsg.setText(msg);
        TextView tv_cancelmsg=dialog.findViewById(R.id.tv_cancelmsg);

        tv_cancelmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public  void searchempdiaolog(String msg){

        final Dialog dialog = new Dialog(SearchEmployeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_searchemploye);

        final EditText edt_searchbyname=dialog.findViewById(R.id.edt_searchbyname);
        final EditText edt_searchbydesignation=dialog.findViewById(R.id.edt_searchbydesignation);

        TextView tv_submitsearch=dialog.findViewById(R.id.tv_submitsearch);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        lp.windowAnimations = R.style.DialogAnimation;

        dialog.getWindow().setAttributes(lp);

        edt_searchbyname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                edt_searchbydesignation.setEnabled(false);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


        edt_searchbydesignation.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                edt_searchbyname.setEnabled(false);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }
            @Override
            public void afterTextChanged(Editable arg0){

            }
        });

        tv_submitsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String   name=edt_searchbyname.getText().toString();
                String  designation=edt_searchbydesignation.getText().toString();
                if (name.length()>0){
                    designation="";
                }else if (designation.length()>0){
                    name="";
                }
                String  user_id=null;
                user_id = preferance.getInstance(getApplicationContext()).getUserId();

                filteremployee(user_id,name,designation,"");

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public  void filteremployee(String UserId,String Name,String Designation,String Department){

        employelist.clear();

        final ProgressDialog progressDialog = new ProgressDialog(SearchEmployeActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialogTitle

        apiInterface= Api.getClient().create(ApiInterface.class);

        Searchmodel user = new Searchmodel(UserId,Name,Designation,Department);

        Call<Searchmodel> call1 = apiInterface.searchemploye(user);
        call1.enqueue(new Callback<Searchmodel>() {
            @Override
            public void onResponse(Call<Searchmodel> call, Response<Searchmodel> response) {

                Searchmodel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                employelist=user1.getListAllEmployee();

                if (status.equals("Success")){
                    ryc_search.setLayoutManager(new LinearLayoutManager(SearchEmployeActivity.this, LinearLayoutManager.VERTICAL, false));
                    // rec_leavereqattachment.setLayoutManager(new Hori);
                    ryc_search.setItemAnimator(new DefaultItemAnimator());
                    SearchAdapter adapter=new SearchAdapter(SearchEmployeActivity.this,employelist);
                    ryc_search.setAdapter(adapter);
                }else {
                    Toast.makeText(SearchEmployeActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Searchmodel> call, Throwable t) {
                call.cancel();
                String str= call.toString();
                faillerdiaolog(str);
                //   Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
