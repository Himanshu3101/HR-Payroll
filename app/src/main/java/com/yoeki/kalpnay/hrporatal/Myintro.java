package com.yoeki.kalpnay.hrporatal;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.yoeki.kalpnay.hrporatal.IntroScreenFragment.AppIntroSampleSlider;

public class Myintro extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {

        //adding the three slides for introduction app you can ad as many you needed
        addSlide(AppIntroSampleSlider.newInstance(R.layout.appintro1));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.appintro2));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.appintro3));

        // Show and Hide Skip and Done buttons
        showStatusBar(false);
        showSkipButton(true);
        // Turn vibration on and set intensity
        // You will need to add VIBRATE permission in Manifest file
        //setVibrate(true);
      //  setVibrateIntensity(30);
        //Add animation to the intro slider

        setDepthAnimation();
    }
    @Override
    public void onSkipPressed() {
        // Do something here when users click or tap on Skip button.
       // Toast.makeText(getApplicationContext(),
         //       getString(R.string.Skip), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
    @Override
    public void onNextPressed() {
        // Do something here when users click or tap on Next button.
    }

    @Override
    public void onDonePressed() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
       // finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something here when slide is changed
    }

}
