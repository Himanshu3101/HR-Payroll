package com.yoeki.kalpnay.hrporatal;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.skyfishjy.library.RippleBackground;

public class SplashActivity extends AppCompatActivity {
    public boolean isFirstStart;
    Context mcontext;

    private static int SPLASH_TIME_OUT = 3000;

    ImageView imageView;
    RippleBackground rippleBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=findViewById(R.id.img_splash);
        rippleBackground=findViewById(R.id.content);

        runthrade();

        rippleBackground.startRippleAnimation();

        if (!isFirstStart){


            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);

                    finish();
                }
            }, SPLASH_TIME_OUT);

        }

    }
    public  void runthrade(){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {

                    //  Launch application introduction screen
                    Intent i = new Intent(SplashActivity.this, Myintro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }/*else {

                    splashcount();
                }*/
            }
        });
        t.start();
    }

    public void splashcount(){

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
