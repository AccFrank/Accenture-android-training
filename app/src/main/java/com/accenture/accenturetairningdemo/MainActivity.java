package com.accenture.accenturetairningdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.accenture.fragment.HistoryFragment;
import com.accenture.fragment.MapFragment;
import com.accenture.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener,SettingsFragment.OnFragmentInteractionListener{

//    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_map:
//                    mTextMessage.setText(R.string.title_map);
                    fragment = MapFragment.newInstance("","");
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_history:
                    fragment = HistoryFragment.newInstance("","");
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
//                    mTextMessage.setText(R.string.title_history);
                    return true;
                case R.id.navigation_settings:
                    fragment = SettingsFragment.newInstance("","");
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
//                    mTextMessage.setText(R.string.title_settings);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        fragment = MapFragment.newInstance("","");
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
//        navigation.setSelectedItemId(R.id.navigation_map);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
