package com.example.myapplication.activity;



import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.fragment.Fragment_one;
import com.example.myapplication.fragment.Fragment_three;
import com.example.myapplication.fragment.Fragment_two;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private ViewPager vp;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    private TabLayout tab;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void getView() {
        vp = findViewById(R.id.vp);
        tab = findViewById(R.id.tab);
    }

    @Override
    protected void getData() {
        tabs.add("标签一");
        tabs.add("标签二");
        tabs.add("标签三");

        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));

        Fragment_one fragment_one = new Fragment_one();
        Fragment_two fragment_two = new Fragment_two();
        Fragment_three fragment_three = new Fragment_three();
        fragments.add(fragment_one);
        fragments.add(fragment_two);
        fragments.add(fragment_three);

        tab.setupWithViewPager(vp);

        FragmentPageAdap fragmentPageAdap = new FragmentPageAdap(getSupportFragmentManager());
        vp.setAdapter(fragmentPageAdap);

    }
    public class FragmentPageAdap extends FragmentPagerAdapter{

        public FragmentPageAdap(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
