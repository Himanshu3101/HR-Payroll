package com.yoeki.kalpnay.hrporatal.setting;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
public class Textclass extends AppCompatTextView{

    public Textclass(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/Calibri.ttf");
        this.setTypeface(face);
    }

    public Textclass(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/Calibri.ttf");
        this.setTypeface(face);
    }

    public Textclass(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/Calibri.ttf");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}