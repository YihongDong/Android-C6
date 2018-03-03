package com.chapters.z.customcomponentsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ZhangShiLin on 2017/1/4.
 */

public class DataSliderView extends View {
    public float mStartValue;//数值范围起点
    public float mEndValue;//数值范围终点
    public float mValue;//当前数值
    private int mWidth,mHeight;//控件的宽、高
    private boolean inRect=false; // 判断是否触摸点在滑标内
    private RectF mRect;      //定义滑标矩形
    private int rectLeft;  //滑标矩形左边的坐标

    private final static int PADDING=15;//内边距
    private final static int LINEHEIGHT=48;//刻度线高度
    private final static int RECTWIDTH=24;//滑标矩形宽度
    private final static int RECTHEIGHT=48;//滑标矩形高度

    public DataSliderView(Context context){
        super(context);
        mStartValue =0;
        mEndValue = 1;
        mValue=mStartValue;
        rectLeft=PADDING-RECTWIDTH/2;
    }

    public DataSliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.DSV_atts);
        mStartValue = a.getFloat(R.styleable.DSV_atts_startvalue,0);
        mEndValue = a.getFloat(R.styleable.DSV_atts_endvalue,1);
        mValue=mStartValue;

        rectLeft=PADDING-RECTWIDTH/2;

    }


    public float getValue(){
        return mValue;
    }

    public void onDraw(Canvas canvas)
    {
        mWidth=getWidth();//获取整个绘制区域的宽度
        mHeight=getHeight();//获取整个绘制区域的高度

        mRect=new RectF(rectLeft,mHeight/2-RECTHEIGHT/2,rectLeft+RECTWIDTH,mHeight/2+RECTHEIGHT/2);//定义矩形滑块的坐标
        Paint mPaint = new Paint();

        canvas.drawLine(PADDING,mHeight/2,mWidth-PADDING,mHeight/2,mPaint); // 绘制x轴线段
        canvas.drawLine(PADDING,mHeight/2,PADDING,mHeight/2-LINEHEIGHT,mPaint);//绘制左刻度线
        canvas.drawLine(mWidth-PADDING,mHeight/2,mWidth-PADDING,mHeight/2-LINEHEIGHT,mPaint);//绘制右刻度线

        mPaint.setStyle(Paint.Style.STROKE);//设置画笔为空心风格
        canvas.drawRect(mRect,mPaint);//绘制矩形滑块
        mPaint.setTextSize(80);//字体大小
        canvas.drawText(String.valueOf(mValue),(float) mRect.left,(float) mRect.top-20,mPaint);//实时显示当前滑块对应的取值
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int w=widthMeasureSpec;
        int h=200;
        setMeasuredDimension(w, h);
    }

    private boolean ifInRect(float x,float y){
        if (x>=mRect.left & x<=mRect.left+mRect.width()){
            if(y>=mRect.top & y<=mRect.top+mRect.height()){
                return true;
            }
        }
        return false;
    }

    private void touch_down(float x,float y){
        if(mRect.contains(x,y)){
            inRect=true;
        }
    }
    private void touch_up(){
        inRect=false;
    }
    private void touch_move(float x,float y){
        if(inRect){
            if((int)x<=PADDING-RECTWIDTH/2){
                rectLeft=PADDING-RECTWIDTH/2;
                mValue=mStartValue;
            }else if((int)x>=mWidth-PADDING-RECTWIDTH/2){
                rectLeft=mWidth-PADDING-RECTWIDTH/2;
                mValue=mEndValue;
            }else {
                rectLeft = (int) x;
                mValue=((x-PADDING)/(mWidth-2*PADDING))*(mEndValue-mStartValue);
                Log.i("aaa",String.valueOf(x));
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_down(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }




}
