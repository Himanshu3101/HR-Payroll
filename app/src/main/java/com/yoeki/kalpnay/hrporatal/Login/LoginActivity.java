package com.yoeki.kalpnay.hrporatal.Login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvforgot,tv_submit;
    ApiInterface apiInterface;
    private EditText edt_username, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        tvforgot.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

    }
 Committed by Himanshu Srivastava
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forgot:

                forgotdialog();

                break;

            case R.id.tv_submit:

                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                if (username.equals("")) {
                    edt_username.setError("fill required field");
                } else if (password.equals("")) {
                    edt_password.setError("fill required field");
                } else {
                    login(username, password);
                    /*Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);*/
                }
                break;
        }
    }
    /////initialize id's
    public void initialize() {

        tvforgot = findViewById(R.id.tv_forgot);
        tv_submit=findViewById(R.id.tv_submit);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

    }
    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(LoginActivity.this);
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
    public void forgotdialog() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.forgot_layout);
        final EditText edt_forgot=dialog.findViewById(R.id.edt_forgot);

        TextView tv_submitforgot=dialog.findViewById(R.id.tv_submitforgot);

        tv_submitforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stremail=edt_forgot.getText().toString();

                if (stremail.equals("")){

                    edt_forgot.setError("Fill required fild");
                }else {

                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setCancelable(false); // set cancelable to false
                    progressDialog.setMessage("Please Wait"); // set message
                    progressDialog.show(); // show progress dialog

                    apiInterface= Api.getClient().create(ApiInterface.class);
                    Forgotresponce user = new Forgotresponce(stremail);

                    Call<Forgotresponce> call1 = apiInterface.forgot(user);
                    call1.enqueue(new Callback<Forgotresponce>() {
                        @Override
                        public void onResponse(Call<Forgotresponce> call, Response<Forgotresponce> response) {
                            Forgotresponce user1 = response.body();
                            progressDialog.dismiss();
                            String str=user1.getMessage();

                            String status=user1.getStatus();
                            if (status.equals("Success")){
                                Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Forgotresponce> call, Throwable t) {
                            call.cancel();
                            progressDialog.dismiss();
                        }
                    });


                }

            }
        });


        dialog.show();
    }

    private void login(String username,String password){
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        apiInterface= Api.getClient().create(ApiInterface.class);
        Loginresponce user = new Loginresponce(username, password);

        Call<Loginresponce> call1 = apiInterface.login(user);
        call1.enqueue(new Callback<Loginresponce>() {
            @Override
            public void onResponse(Call<Loginresponce> call, Response<Loginresponce> response) {
                Loginresponce user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();

                if (status.equals("success")){
                    Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();

                    preferance.getInstance(getApplicationContext()).saveuserLogin(user1.getUserId());
                    preferance.getInstance(getApplicationContext()).saveuserName(user1.getUserName());

                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else{
                    faillerdiaolog(str);
                    // Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Loginresponce> call, Throwable t) {
                call.cancel();
                faillerdiaolog("somthing went wrong");
             //   Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

}
