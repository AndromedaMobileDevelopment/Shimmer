package com.development.mobile.andromeda.shimmer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ProfileFragment extends Fragment {
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_profile, null);
        TabLayout tabs = (TabLayout) view.findViewById(R.id.materialup_tabs);
        viewPager  = (ViewPager) view.findViewById(R.id.material_viewpager);
        tabs.addTab(tabs.newTab().setText("Профиль").setTag(1));


        viewPager.setAdapter(new TabsAdapter(getActivity().getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    public static Fragment newInstance() {
        return new ProfileFragment();
    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int i) {
            switch(i) {
                case 0: return InfoProfileFragment.newInstance();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "Профиль";
            }
            return "";
        }
    }
}
