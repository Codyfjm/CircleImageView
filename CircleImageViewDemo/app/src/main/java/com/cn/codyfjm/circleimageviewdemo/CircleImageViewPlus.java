package com.cn.codyfjm.circleimageviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义圆形ImageView组件
 * author：codyfjm on 18/1/29
 * email：CodyFeng@lansion.cn
 * company:Lansion Software
 * Copyright © 2018 Lansion. All rights reserved.
 */
public class CircleImageViewPlus extends ImageView {

    private Paint mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mRawBitmap;
    private BitmapShader mShader;
    private Matrix mMatrix = new Matrix();

    public CircleImageViewPlus(Context context) {
        super(context);
    }

    public CircleImageViewPlus(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageViewPlus(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 重画组件
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap rawBitmap = getBitmap(getDrawable());
        if (rawBitmap != null){
            int viewWidth = getWidth();
            int viewHeight = getHeight();
            int viewMinSize = Math.min(viewWidth,viewHeight);
            float dstWidth = viewMinSize;
            float dstHeight = viewMinSize;
            if (mShader == null || !rawBitmap.equals(mRawBitmap)){
                mRawBitmap = rawBitmap;
                mShader = new BitmapShader(mRawBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }

            if (mShader != null){
                mMatrix.setScale(dstWidth/rawBitmap.getWidth(),dstHeight/rawBitmap.getHeight());
                mShader.setLocalMatrix(mMatrix);
            }

            mPaintBitmap.setShader(mShader);
            float radius = viewMinSize / 2.0f;
            canvas.drawCircle(radius,radius,radius,mPaintBitmap);

        }else {
            super.onDraw(canvas);
        }
    }

    /**
     * 获取图片
     * @param drawable
     * @return
     */
    private Bitmap getBitmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            return ((BitmapDrawable) drawable).getBitmap();
        }else if (drawable instanceof ColorDrawable){
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.left;
            int color = ((ColorDrawable) drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            Canvas canvas =  new Canvas(bitmap);
            canvas.drawARGB(Color.alpha(color),Color.red(color),Color.green(color),Color.blue(color));
            return bitmap;
        }else {
            return null;
        }
    }
}
