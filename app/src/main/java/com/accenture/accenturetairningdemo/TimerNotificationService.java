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
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


public class TimerNotificationService extends Service {

    public TimerNotificationService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate()");
        final Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(TimerNotificationService.this.getApplicationContext(), "Reminder ...",
                        Toast.LENGTH_LONG).show();            }
        });

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
                        Log.d("", "Showing Toast..");
                        handler.handleMessage(new Message());
                }
                }
            }
        };

        new Thread(pollingThread).start();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Log.e("TAG", "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);

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
