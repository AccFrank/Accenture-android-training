package com.atom.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_launcher, "List"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_launcher, "Blank"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_launcher, "Setting"));
        bottomNavigationBar.initialise();

        bottomNavigationBar.setTabSelectedListener(bottomBarListener);
    }

    BottomNavigationBar.OnTabSelectedListener bottomBarListener = new BottomNavigationBar.OnTabSelectedListener(){
        @Override
        public void onTabSelected(int position) {
            //from left to right, start with 0

        }
        @Override
        public void onTabUnselected(int position) {
        }
        @Override
        public void onTabReselected(int position) {
        }
    };
}
