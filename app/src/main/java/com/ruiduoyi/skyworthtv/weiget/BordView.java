package com.ruiduoyi.skyworthtv.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ruiduoyi.skyworthtv.R;

/**
 * Created by Chen on 2018-08-16.
 */

public class BordView extends View {
    private static final String TAG = BordView.class.getSimpleName();
    private int paintColor;
    private Paint mPaintBig;
    private Paint mPaintSmall;
    private int mWidth;
    private int mHeight;
    private int corners = 40;
    private Path pathLeftTop;
    private Path pathRightTop;
    private Path pathLeftBottom;
    private Path pathRightBottom;
    private int strokeWidthBig = 20;
    private int strokeWidthSmall = 2;

    public BordView(Context context) {
        this(context,null);
    }

    public BordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        //Log.d(TAG, "onSizeChanged: mWidth"+mWidth);
        //Log.d(TAG, "onSizeChanged: mHeight"+mHeight);

    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paintColor = getResources().getColor(R.color.linFragment_blue);
        mPaintSmall = new Paint();
        mPaintSmall.setColor(paintColor);
        mPaintSmall.setStyle(Paint.Style.FILL);
        mPaintSmall.setStrokeWidth(strokeWidthSmall);

        mPaintBig = new Paint();
        mPaintBig.setColor(paintColor);
        mPaintBig.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintBig.setStrokeWidth(strokeWidthBig);
        mPaintBig.setAntiAlias(true);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawPath(pathLeftTop,mPaintBig);
        //canvas.drawPath(pathRightTop,mPaintBig);
        //canvas.drawPath(pathLeftBottom,mPaintBig);
        //canvas.drawPath(pathRightBottom,mPaintBig);
        //canvas.drawLine(corners,0,mWidth-corners,0,mPaintBig);
        //canvas.drawLine(corners,mHeight,mWidth-corners,mHeight,mPaintBig);

        //canvas.drawCircle(mWidth/4,mHeight/4,10,mPaintBig);
        int x1 = strokeWidthBig/4 -strokeWidthSmall/2;
        int x2 = mWidth -(strokeWidthBig/4 -strokeWidthSmall/2);
        canvas.drawLine(x1,corners,x1,mHeight-corners,mPaintSmall);
        canvas.drawLine(x2,corners,x2,mHeight-corners,mPaintSmall);

        Paint paintTop = new Paint();
        paintTop.setColor(paintColor);
        paintTop.setStyle(Paint.Style.STROKE);
        paintTop.setStrokeWidth(strokeWidthBig);
        paintTop.setAntiAlias(true);
        //上
        Path pathTop = new Path();
        pathTop.moveTo(0,corners);
        pathTop.quadTo(0,0,corners,0);
        pathTop.lineTo(mWidth-corners,0);
        pathTop.quadTo(mWidth,0,mWidth,corners);
        canvas.drawPath(pathTop,paintTop);


        //下
        Paint paintBottom = new Paint();
        paintBottom.setColor(paintColor);
        paintBottom.setStyle(Paint.Style.STROKE);
        paintBottom.setStrokeWidth(strokeWidthBig);
        paintBottom.setAntiAlias(true);
        Path pathBottom = new Path();
        pathBottom.moveTo(0,mHeight-corners);
        pathBottom.quadTo(0,mHeight,corners,mHeight);
        pathBottom.lineTo(mWidth-corners,mHeight);
        pathBottom.quadTo(mWidth,mHeight,mWidth,mHeight-corners);
        canvas.drawPath(pathBottom,paintBottom);
    }
}
