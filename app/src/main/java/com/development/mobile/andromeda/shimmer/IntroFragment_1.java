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

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class IntroFragment_1 extends Fragment {
    private Shimmer shimmer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intro_1_layout, null);
        ShimmerTextView shimmerTextView = (ShimmerTextView) view.findViewById(R.id.shimmer_tv);
        ImageView news = (ImageView) view.findViewById(R.id.news_intro);
        Animation newsAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.trans);
        news.startAnimation(newsAnim);
        shimmer = new Shimmer();
        shimmer.start(shimmerTextView);
        return view;
    }
    public static Fragment newInstance() {
        return new IntroFragment_1();
    }
}
