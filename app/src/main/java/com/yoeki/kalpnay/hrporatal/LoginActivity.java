package com.yoeki.kalpnay.hrporatal;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.yoeki.kalpnay.hrporatal.HomeMenu.HomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvforgot,tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        tvforgot.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forgot:

                forgotdialog();

                break;

            case R.id.tv_submit:

                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                  finish();

                break;

        }
    }
    /////initialize id's
    public void initialize() {

        tvforgot = findViewById(R.id.tv_forgot);
        tv_submit=findViewById(R.id.tv_submit);
    }

    public void forgotdialog() {


        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.forgot_layout);

        dialog.show();
    }
}
