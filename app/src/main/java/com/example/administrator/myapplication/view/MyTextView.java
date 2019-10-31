package com.example.administrator.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

public class MyTextView extends TextView {
    private int titleTextColor;
    public MyTextView(Context context) {
        super(context);

    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }
    public void init(Context context,AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        titleTextColor=ta.getColor(R.styleable.MyTextView_titleTextColor,0);

        this.setTextColor(titleTextColor);
        ta.recycle();
//        this.setTextColor(context.getResources().getColor(R.color.colorPrimary));
    }


}
