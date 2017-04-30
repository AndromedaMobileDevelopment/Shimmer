package com.development.mobile.andromeda.shimmer;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestInfoFragment extends AppCompatActivity implements View.OnClickListener{
    private TestFragment testFrag;
    private FragmentTransaction fg;
    private Button startTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info_fragment);
        startTest = (Button) findViewById(R.id.StartTest);
        startTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startTest.setVisibility(View.GONE);
        testFrag = new TestFragment();
        fg = getFragmentManager().beginTransaction();
        fg.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fg.add(R.id.fragCont, testFrag).commit();
    }
}
