package com.chapters.z.customcomponentsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/5.
 */

public class FlickerTextView extends TextView {
    private int mcolor1,mcolor2,mcolor3,mcolor4;//边框随机宣示的四种颜色
    private int mWidth,mHeight;//控件宽度高度
    private Rect mRect;//边框矩形
    final static int PADDING=5;//边框的外边距
    public float mStrokeWidth=5;//边框线宽度

    public FlickerTextView(Context context){
        super(context);
        mcolor1= Color.BLUE;
        mcolor2=Color.GREEN;
        mcolor3= Color.RED;
        mcolor4= Color.YELLOW;
    }


    public FlickerTextView(Context context, AttributeSet atts) {
        super(context, atts);

        TypedArray a = context.obtainStyledAttributes(atts,
                R.styleable.FTV_atts);
        mcolor1=a.getColor(R.styleable.FTV_atts_fcolor1, Color.BLUE);
        mcolor2=a.getColor(R.styleable.FTV_atts_fcolor2, Color.GREEN);
        mcolor3=a.getColor(R.styleable.FTV_atts_fcolor3, Color.RED);
        mcolor4=a.getColor(R.styleable.FTV_atts_fcolor4, Color.YELLOW);

    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);//调用父类方法，否则textivew的文字不会显示

        mWidth=getWidth();
        mHeight=getHeight();
        mRect=new Rect(PADDING,PADDING,mWidth-PADDING,mHeight-PADDING);
        Paint mPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);//设置画笔为空心风格
        mPaint.setStrokeWidth(mStrokeWidth);
        switch ((int) (Math.random()*3)){
            case 0:
                mPaint.setColor(mcolor1);
                break;
            case 1:
                mPaint.setColor(mcolor2);
                break;
            case 2:
                mPaint.setColor(mcolor3);
                break;
            case 3:
                mPaint.setColor(mcolor4);
                break;

            default:
                mPaint.setColor(mcolor1);
        }

        canvas.drawRect(mRect,mPaint);       ;
    }


}
