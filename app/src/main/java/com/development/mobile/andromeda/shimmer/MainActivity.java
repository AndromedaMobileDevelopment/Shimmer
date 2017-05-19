package com.development.mobile.andromeda.shimmer;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tabs = (TabLayout) findViewById(R.id.materialup_tabs);
        viewPager  = (ViewPager) findViewById(R.id.materialup_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.materialup_appbar);
        tabs.addTab(tabs.newTab().setText("Новости").setTag(1));
        tabs.addTab(tabs.newTab().setText("Игры").setTag(2));
        tabs.addTab(tabs.newTab().setText("Отзывы и рецензии").setTag(3));
        tabs.addTab(tabs.newTab().setText("Профиль").setTag(4));

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int i) {
            switch(i) {
                case 0: return NewsFragment.newInstance();
                case 1: return GamesFragment.newInstance();
                case 2: return ReviewsFragment.newInstance();
                case 3: return ProfileFragment.newInstance();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "Новости";
                case 1: return "Игры";
                case 2: return "Отзывы и рецензии";
                case 3: return "Профиль";
            }
            return "";
        }
    }
}
