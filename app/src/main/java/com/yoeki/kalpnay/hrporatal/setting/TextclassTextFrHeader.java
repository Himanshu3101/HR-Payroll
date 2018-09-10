package com.yoeki.kalpnay.hrporatal.setting;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TextclassTextFrHeader extends AppCompatTextView{

    public TextclassTextFrHeader(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/PlayfairDisplay-Regular.ttf");
        this.setTypeface(face);
    }

    public TextclassTextFrHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/PlayfairDisplay-Regular.ttf");
        this.setTypeface(face);
    }

    public TextclassTextFrHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/PlayfairDisplay-Regular");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}