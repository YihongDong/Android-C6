package com.example.me.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    public TextView tv;
    private Button bt;
    private boolean bl=false;;
    private int wt=0;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int left=tv.getLeft();
            wt=getScreenHeight();
            if(msg.what == 1) {
               tv.setLeft((left+20)%wt);
            }
            super.handleMessage(msg);
        }
    };


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt=(Button)this.findViewById(R.id.button1);
        tv = (TextView)this.findViewById(R.id.text1);

        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
               // loadImage1();
               //loadImage2();
               // loadImage3();
               // loadImage4();
                Thread2();
            }
        });

    }


//view.post(runnable)
    private void Thread1(){
        new Thread(new Runnable() {
            public void run() {
                bl=!bl;
                while (bl) {
                final int left=tv.getLeft();
               tv.post(new Runnable() {
                   public void run() {
                       wt = getScreenHeight();
                       tv.setLeft((left + 20) % wt);
                   }
               }
               );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            }
        }).start();
    }



    //handler method
    private void Thread2(){
        new Thread(new Runnable() {
            public void run() {
                bl=!bl;
                while (bl) {
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;
                    msg.sendToTarget();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }

        //runonuithread
        private void Thread3(){
            new Thread(new Runnable() {
                public void run() {
                    bl=!bl;
                    while (bl) {
                    MainActivity.this.runOnUiThread(new Runnable() // 工作线程刷新UI
                    {                         //这部分代码将在UI线程执行，实际上是runOnUiThread post Runnable到UI线程执行了
                        final int left=tv.getLeft();
                        public void run() {
                            wt = getScreenHeight();
                            tv.setLeft((left + 20) % wt);
                                            }
                                        }
                                );
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                    }
                }
            }).start();

        }

    private int getScreenHeight() {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        return width;
    }

}
