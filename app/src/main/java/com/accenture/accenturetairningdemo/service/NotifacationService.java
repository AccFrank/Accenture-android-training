package com.accenture.accenturetairningdemo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.accenture.accenturetairningdemo.MainActivity;
import com.accenture.accenturetairningdemo.R;

public class NotifacationService extends Service {
    public static final String ACTION = "com.dfm.PollingService";
    private MyBinder mBinder = new MyBinder();
    public NotifacationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate()");
        Notification.Builder builder1 = new Notification.Builder(this);
        builder1.setSmallIcon(R.drawable.bluemap_hover); //设置图标
        builder1.setTicker("显示第二个通知");
        builder1.setContentTitle("这是我们的service"); //设置标题
        builder1.setContentText("点击查看详细内容"); //消息内容
        builder1.setWhen(System.currentTimeMillis()); //发送时间
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder1.setAutoCancel(true);//打开程序后图标消失
        Intent intent =new Intent (this,MainActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(this, 0, intent, 0);
        builder1.setContentIntent(pendingIntent);
        Notification notification1 = builder1.build();

        startForeground(1, notification1);
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Log.e("TAG", "onStartCommand()");
        new PollingThread().start();
        return super.onStartCommand(intent, flags, startId);

    }
    int count = 0;
    class PollingThread extends Thread {
        @Override
        public void run() {
            System.out.println("Polling...");
            count ++;
            //当计数能被5整除时弹出通知
            if (count % 5 == 0) {
                System.out.println("New message!");
            }
        }
    }
    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG", "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "onBind()");
        return mBinder;
    }
   public class MyBinder extends Binder {

        public void startDownload() {
            Log.e("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }

    }
}
