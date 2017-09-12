package com.accenture.accenturetairningdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {


    @BindView(R.id.Lan_cn)
    TextView LanCn;
    @BindView(R.id.Lan_en)
    TextView LanEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Lan_cn, R.id.Lan_en})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Lan_cn:
                switchLan(Locale.CHINESE);
                break;
            case R.id.Lan_en:
                switchLan(Locale.ENGLISH);
                break;
        }
    }

    private void switchLan(Locale locale) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
// 应用用户选择语言
        config.locale = locale;
        resources.updateConfiguration(config, dm);
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("param", "123");
        startActivity(intent);
    }
}
