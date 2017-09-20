package com.accenture.accenturetairningdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.accenture.accenturetairningdemo.fragment.ContactFragment;
import com.accenture.accenturetairningdemo.fragment.ManageFragment;
import com.accenture.accenturetairningdemo.fragment.MeFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private ArrayList<Fragment> fragments;
    private boolean isFirstClick = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.bluemap_hover, "蓝图").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.monitor_hover, "监控").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.me_hover, " 我的").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();
//
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, ManageFragment.newInstance("",""));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ManageFragment.newInstance("",""));
        fragments.add(ContactFragment.newInstance("",""));
        fragments.add(MeFragment.newInstance("",""));
//        fragments.add(TvFragment.newInstance("Movies & TV"));
//        fragments.add(GameFragment.newInstance("Games"));
        return fragments;
    }





    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
//            if(isFirstClick){
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment fragment = fragments.get(0);
//                ft.remove(fragment);
//                ft.commitAllowingStateLoss();
//                isFirstClick = false;
//            }
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
//                if (fragment.isAdded()) {
//                    ft.replace(R.id.layFrame, fragment);
//                } else {
//                    ft.add(R.id.layFrame, fragment);
//                }
                ft.replace(R.id.layFrame, fragment);
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

}
