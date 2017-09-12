package com.accenture.accenturetairningdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;

public class NaviActivity extends AppCompatActivity {


    TextView param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        ButterKnife.bind(this);
        String prarm = this.getIntent().getStringExtra("param");
        param = (TextView) this.findViewById(R.id.toFirstPage);
        param.setText(prarm);

    }
}
