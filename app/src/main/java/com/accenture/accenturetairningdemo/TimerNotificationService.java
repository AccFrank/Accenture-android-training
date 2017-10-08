package com.accenture.accenturetairningdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;


public class TimerNotificationService extends Service {

    public TimerNotificationService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate()");
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Notification.Builder builder1 = new Notification.Builder(TimerNotificationService.this);
                //builder1.setSmallIcon(R.drawable.bluemap_hover); //设置图标
                builder1.setTicker("整点提醒");
                builder1.setContentTitle("整点提醒"); //设置标题
                builder1.setContentText("整点提醒"); //消息内容
                builder1.setWhen(System.currentTimeMillis()); //发送时间
                builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
                builder1.setAutoCancel(true);//打开程序后图标消失

                Notification notification1 = builder1.build();

                startForeground(1, notification1);
            }
        };

        Runnable pollingThread = new Runnable() {

            @Override
            public void run() {
                int count = 0;
                System.out.println("Polling...");
                while (1==1) {
                    count++;
                    //当计数能被5整除时弹出通知
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    if (count % 5 == 0) {
                        handler.handleMessage(new Message());
                }
                }
            }
        };
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
        return null;
    }
    public class MyBinder extends Binder {

        public void startDownload() {
            Log.e("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }

    }}
