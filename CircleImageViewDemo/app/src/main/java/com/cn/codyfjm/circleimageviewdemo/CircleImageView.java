package com.cn.codyfjm.circleimageviewdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cn.codyfjm.circleimageviewdemo.R;

/**
 * 自定义圆形imageview
 * 优点：圆形iamgeview
 * 缺点：无法动态加载图片
 * author：codyfjm on 18/1/28
 * email：CodyFeng@lansion.cn
 * company:Lansion Software
 * Copyright © 2018 Lansion. All rights reserved.
 */
@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView{

    private int width;//宽
    private int height;//高
    private int radius;//半径

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = Math.min(height,width) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);//设置画布背景方便观察
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.logo);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        canvas.drawCircle(width/2,height/2,radius,paint);
    }
}
