package com.zwj.testandroidserver.view;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zwj.testandroidserver.R;


/**
 * <b>Create Date:</b> 2018/1/31<br>
 * <b>Author:</b> Zhongwenjie <br>
 * <b>Description:</b>
 */

public class GuaGuaKa extends View {

    private Paint pathPaint;
    private Path path;
    private Bitmap cacheBitmap ;
    private Canvas cacheCanvas;
    private Rect srcRect;
    private Rect destRect;

    private Bitmap backBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.add_into);

    public GuaGuaKa(Context context) {
        this(context, null);
    }

    /**
     *初始化view
     */
    public GuaGuaKa(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(Color.GRAY);
        pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setDither(true);
        pathPaint.setColor(Color.BLACK);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(50);
        pathPaint.setStrokeCap(Paint.Cap.ROUND);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);
        path = new Path();
    }

    /**
     *测量view大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *实际绘制内容
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(backBitmap, 0, 0, null);
        pathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        cacheCanvas.drawPath(path, pathPaint);
        canvas.drawBitmap(cacheBitmap, 0, 0, null);
    }

    /**
     *确定view大小
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        backBitmap = Bitmap.createScaledBitmap(backBitmap, getMeasuredWidth(), getMeasuredHeight(), true);
    }

    /**
     *确定子view布局
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        cacheBitmap = Bitmap.createBitmap(getWidth() - getPaddingLeft() - getPaddingRight(), getHeight() - getPaddingTop() - getPaddingBottom(), Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas(cacheBitmap);
        cacheCanvas.drawColor(Color.GRAY);
    }

    //手指按下时对应的x坐标和y坐标
    private float pointerX;
    private float pointerY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pointerX = event.getX();
                pointerY = event.getY();
                path.moveTo(pointerX, pointerY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                pointerX = event.getX();
                pointerY = event.getY();
                break;
        }
        invalidate();
        return true;
    }
}
