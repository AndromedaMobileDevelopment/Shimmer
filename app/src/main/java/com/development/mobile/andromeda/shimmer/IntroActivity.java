package com.development.mobile.andromeda.shimmer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.romainpiel.shimmer.Shimmer;

public class IntroActivity extends AppIntro {
    private IntroFragment_1 introFragment_1;
    private IntroFragment_2 introFragment_2;
    private IntroFragment_3 introFragment_3;
    private IntroFragment_4 introFragment_4;
    private Shimmer shimmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introFragment_1 = new IntroFragment_1();
        introFragment_2 = new IntroFragment_2();
        introFragment_3 = new IntroFragment_3();
        introFragment_4 = new IntroFragment_4();

        addSlide(introFragment_1);
        addSlide(introFragment_2);
        addSlide(introFragment_3);
        addSlide(introFragment_4);

        setBarColor(Color.parseColor("#141d26"));
        setSeparatorColor(Color.parseColor("#141d26"));
        setColorDoneText(Color.parseColor("#ffffff"));
        setColorSkipButton(Color.parseColor("#ffffff"));
        setIndicatorColor(Color.parseColor("#ffffff"), Color.parseColor("#FF8B8A8A"));

        showSkipButton(true);
        setProgressButtonEnabled(true);

        setVibrate(true);
        setVibrateIntensity(30);

    }

    @Override
    public void onSlideChanged(@Nullable android.support.v4.app.Fragment oldFragment, @Nullable android.support.v4.app.Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    @Override
    public void onDonePressed(android.support.v4.app.Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(android.support.v4.app.Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
