package com.example.me.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
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
    private TextView tx;
    private final static String URL="https://www.baidu.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt=(Button)this.findViewById(R.id.button1);
        tx=(TextView)findViewById(R.id.text1);
        //设置按钮的点击事件
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                loadHtmlText();
            }
        });
    }
    //调用异步任务方法
    private void loadHtmlText(){
        new DownloadHtmlTextTask().execute();
    }
    //读取inputStream转化为HTML源码
    public static byte[] readStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        inputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    private String loadHtmlTextFromNetwork(){
        //图片的链接地址
        String url=URL;
        //连接链接并下载html文本
        try{
            java.net.URL textURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) textURL.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            byte[] data = readStream(is);
            //将html文本转化为String类型用于textview文本替换
            String html = new String(data);
            is.close();
            return html;
        }catch(IOException e){
            return null;
        }
    }
    //AsyncTask
    private class DownloadHtmlTextTask extends AsyncTask<String, Void, String> {
        //执行loadHtmlTextFromNetwork()返回String给onPostExecute
        protected String doInBackground(String... urls) {
            return loadHtmlTextFromNetwork();
        }
        //接受String并将textview的文字替换
        protected void onPostExecute(String result) {
            tx.setText(result);
        }
    }
}
