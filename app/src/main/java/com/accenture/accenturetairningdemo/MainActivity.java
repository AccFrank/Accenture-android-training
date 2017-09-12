package com.accenture.accenturetairningdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView toFirstPage;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        textView = (TextView) this.findViewById(R.id.textView);
        toFirstPage = (TextView) this.findViewById(R.id.toFirstPage);
//        toFirstPage.setText("hello world");
//        toFirstPage.setText("导航页面");
//        EventBus.getDefault().register(this);
        toFirstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("test log","点击文本");
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("param", "123");
                startActivity(intent);

            }
        });
    }
}
