package com.development.mobile.andromeda.shimmer;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class IntroFragment_4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intro_4_layout, null);
        ImageView memes = (ImageView) view.findViewById(R.id.intro4);
        Animation rotate = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.logo_rotate);
        memes.startAnimation(rotate);
        return view;
    }

    public static Fragment newInstance() {
        return new IntroFragment_4();
    }
}
