package com.chapters.z.customcomponentsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/1/5.
 */

public class SliderDataFlickerView extends LinearLayout {
    private DataSliderView mDSView;
    private FlickerTextView mFTView;



    public SliderDataFlickerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        addViews(context);
        //inflateViews(context);

        mDSView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mFTView.mStrokeWidth=mDSView.getValue()*50;
                mFTView.invalidate();
                return false;
            }
        });

    }

    //用inflate布局xml文件的方法定义自定义控件的内容
    private void inflateViews(Context context){
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.sliderdataflickerlayout,this);
        mFTView= (FlickerTextView) view.findViewById(R.id.textView);
        mDSView= (DataSliderView) view.findViewById(R.id.dataslider);

    }
   //用代码加入自定义控件所包含的子View
    private void addViews(Context context){
        this.setOrientation(LinearLayout.VERTICAL);//设置成纵向排列

        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT );

        mFTView=new FlickerTextView(context);
        mFTView.setText("flickerTextView");
        int x=dip2px(context,10);//转换成与密度无关像素单位
        mFTView.setPadding(x,x,x,x);

        this.addView(mFTView,p);

        p=new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT );
        mDSView=new DataSliderView(context);
        this.addView(mDSView,p);
    }

    //dip转成px值
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
