package com.chapters.z.customcomponentsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ZhangShiLin on 2017/1/4.
 */

public class RotateSelfView extends View {
      int screenHeight ;
      int screenWidth;
    static final float HANDLERLENGTH=300;
    float init_second=180;
    float angle;
    float init_minute=180;
    float angle1;
    float init_hour=180;
    float angle2;


    public RotateSelfView(Context context){
        super(context);
        angle=init_second;
        angle1=init_minute;
        angle2=init_hour;
        new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    if(angle<360-6){
                        angle+=6;
                    }else{
                        angle=0;
                    }
                    if(angle1<360-0.1){
                        angle1+=0.1;
                    }else{
                        angle1=0;
                    }
                    if(angle2<360-0.1/12){
                        angle2+=0.1/12;
                    }else{
                        angle2=0;
                    }
                    invalidate();//重新调用onDraw
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();


    }
    public RotateSelfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.DSV_atts);
    }
    public void onDraw(Canvas canvas)
    {
        int mWidth=getWidth();//获取整个绘制区域的宽度
        int mHeight=getHeight();//获取整个绘制区域的高度
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);

        int strokewideth=20;
        int longHeight=80;
        int shortHeight=40;
        float lineTop=mHeight/4;
        float lineBottom;
        String time;
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(50);
        for (int i = 0; i <= 360; i++) {
            if (i % 30 == 0) {
                lineBottom = lineTop + longHeight;// 长刻度线宽度
                paint.setStrokeWidth(strokewideth);// 长刻度线宽度
                time=String.valueOf(i/30);// 时钟刻度值
                if (12==i/30)
                    time="0";
            } else {
                lineBottom = lineTop + shortHeight;// 短刻度线宽度
                paint.setStrokeWidth(strokewideth/2);// 短刻度线宽度
                time="";
            }
            if (i % 6 == 0) {
                canvas.save();
                canvas.rotate(i, mWidth/2, mHeight/2);// 顺时针旋转 6 度
                canvas.drawLine(mWidth/2, lineTop, mWidth/2, lineBottom, paint);// 绘制时钟刻
                canvas.drawText(time,mWidth/2-25,lineBottom+50,paint);// 绘制时钟刻度值
                canvas.restore();
            }
        }

        canvas.save();
        canvas.drawCircle(mWidth/2,mHeight/2,50,paint);//在屏幕中心画个实心圆
        canvas.rotate(angle, mWidth/2, mHeight/2);//顺时针旋转6度
        canvas.drawLine(mWidth/2, mHeight/2, mWidth/2, mHeight/2+HANDLERLENGTH, paint);//绘制时钟刻度线
        Paint paint1=new Paint();
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(10);
        paint1.setStyle(Paint.Style.FILL);
        canvas.rotate(angle1-angle, mWidth/2, mHeight/2);//顺时针旋转6度
        canvas.drawLine(mWidth/2, mHeight/2, mWidth/2, mHeight/2+HANDLERLENGTH, paint);//绘制时钟刻度线
        Paint paint2=new Paint();
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(10);
        paint2.setStyle(Paint.Style.FILL);
        canvas.rotate(angle2-angle1, mWidth/2, mHeight/2);//顺时针旋转6度
        canvas.drawLine(mWidth/2, mHeight/2, mWidth/2, mHeight/2+HANDLERLENGTH, paint);//绘制时钟刻度线
        canvas.restore();
    }
}
