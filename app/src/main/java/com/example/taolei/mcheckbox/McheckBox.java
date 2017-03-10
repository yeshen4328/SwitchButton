package com.example.taolei.mcheckbox;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

/**
 * Created by Tao Lei on 2017/3/7.
 */

public class McheckBox extends View{
    boolean stat = false, click = false, first = true;
    int viewWith, viewHeight, radius, btnRadius;
    int startx, starty = 0, cx, cy;
    int startPos, endPos;
    int speed;
    int btnPos = 0;
    String Tag = "onMeasure";
    Paint bottom = new Paint();
    Paint circle = new Paint();
    Paint lframe = new Paint();
    Paint rframe = new Paint();
    public McheckBox(Context context, AttributeSet attr)
    {
        super(context, attr);
        bottom.setColor(Color.argb(255, 191, 191, 191));
        circle.setColor(Color.argb(255, 232, 232, 232));
        lframe.setColor(Color.argb(255, 90, 245, 128));
        rframe.setColor(Color.argb(255, 191, 191, 191));
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureHeight(widthMeasureSpec), measureHeight(heightMeasureSpec));
        btnRadius = this.getHeight() / 2;
        viewHeight = this.getHeight() * 2 / 3 ;
        viewWith = this.getWidth() - 2 * btnRadius;
        radius = viewHeight / 2;

        speed = viewWith / 15;
        cx = btnRadius;
        cy = this.getHeight() / 2;
        startx = btnRadius;
        starty = (this.getHeight() - viewHeight) / 2;

        startPos = startx;
        endPos = this.getWidth() - startPos;
    }
    private int measureHeight(int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int result=0; //结果
        int specMode=MeasureSpec.getMode(heightMeasureSpec);
        int specSize=MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:  // 子容器可以是声明大小内的任意大小
                Log.e(Tag, "子容器可以是声明大小内的任意大小");
                Log.e(Tag, "大小为:"+specSize);
                result=specSize;
                break;
            case MeasureSpec.EXACTLY: //父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.  比如EditTextView中的DrawLeft
                Log.e(Tag, "父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间");
                Log.e(Tag, "大小为:"+specSize);
                result=specSize;
                break;
            case MeasureSpec.UNSPECIFIED:  //父容器对于子容器没有任何限制,子容器想要多大就多大. 所以完全取决于子view的大小
                Log.e(Tag, "父容器对于子容器没有任何限制,子容器想要多大就多大");
                Log.e(Tag, "大小为:"+specSize);
                result=1500;
                break;
            default:
                break;
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       if(first)
       {
           canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, bottom);
           canvas.drawCircle(cx, cy, btnRadius, circle);
           first = false;
           return;
       }
        if(btnPos == 0)
        {

            if(cx <= endPos)
            {
                canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, bottom);
                canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, rframe);
                canvas.drawRoundRect(new RectF(startx, starty, cx, starty + viewHeight), radius, radius, lframe);
                canvas.drawCircle(cx, cy, btnRadius, circle);
                cx += speed;
                this.postInvalidate();
                return;
            }
            btnPos = 1;
            canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, bottom);
            canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, rframe);
            canvas.drawRoundRect(new RectF(startx, starty, cx, starty + viewHeight), radius, radius, lframe);
            cx -= speed;
            canvas.drawCircle(cx, cy, btnRadius, circle);
        }
        else
        {

            if(cx >= startPos)
            {
                canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, bottom);
                canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, lframe);
                canvas.drawRoundRect(new RectF(cx, starty, startx + viewWith, starty + viewHeight), radius, radius, rframe);
                canvas.drawCircle(cx, cy, btnRadius, circle);
                cx -= speed;
                this.postInvalidate();
                return;
            }
            btnPos = 0;
            canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, bottom);
            canvas.drawRoundRect(new RectF(startx, starty, startx + viewWith, starty + viewHeight), radius, radius, lframe);
            canvas.drawRoundRect(new RectF(cx, starty,  startx + viewWith, starty + viewHeight), radius, radius, rframe);
            cx += speed;
            canvas.drawCircle(cx, cy, btnRadius, circle);
        }
    }

    public boolean getStat()
    {
        return stat;
    }
    public void triger()
    {
        if(!click) {
            stat = click = true;
            this.postInvalidate();
        }
        else {
            stat = click = false;
            this.postInvalidate();
        }
    }

}
