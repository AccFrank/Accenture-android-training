package com.accenture.accenturetairningdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements
        MgntFragment.OnFragmentInteractionListener,
        ContactListFragment.OnFragmentInteractionListener,
        SettingFragment.OnFragmentInteractionListener,
        BottomNavigationBar.OnTabSelectedListener {

    private MgntFragment mMgntFragment = null;
    private ContactListFragment mContactListFragment = null;
    private SettingFragment mSettingFragment = null;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onStart() {
        System.out.println("onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        System.out.println("onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        System.out.println("onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        System.out.println("onResume()");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "管理"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "通讯录"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我的"));
        bottomNavigationBar.initialise();
        bottomNavigationBar.setTabSelectedListener(this);

        setDefaultFragment();

        Intent startIntent = new Intent(this, TimerNotificationService.class);
        startService(startIntent);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(new AirplaneModeBroadcastReceiver(), intentFilter);

        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.provider.Telephony.SMS_RECEIVED");
        intentFilter2.setPriority(1000);
        registerReceiver(new SmsReceiver(), intentFilter2);
    }

    class AirplaneModeBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onTabSelected(int position) {
        Log.d("TAB", "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mMgntFragment == null) {
                    mMgntFragment = MgntFragment.newInstance("管理", "管理");
                }
                transaction.replace(R.id.tabs, mMgntFragment);
                break;
            case 1:
                if (mContactListFragment == null) {
                    mContactListFragment = ContactListFragment.newInstance("联系人","联系人" );
                }
                transaction.replace(R.id.tabs, mContactListFragment);
                break;
            case 2:
                if (mSettingFragment == null) {
                    mSettingFragment = SettingFragment.newInstance("我的", "我的");
                }
                transaction.replace(R.id.tabs, mSettingFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mMgntFragment = MgntFragment.newInstance("管理", "管理");
        transaction.replace(R.id.tabs, mMgntFragment);
        transaction.commit();
    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


}
