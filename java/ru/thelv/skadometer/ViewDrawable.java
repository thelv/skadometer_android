package ru.thelv.skadometer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class ViewDrawable extends View
{
    int w, h;
    Typeface font;

    double paddingMain=27.5/452, graphFontSize=0.025, indicatorFontSize=0.80, lineAxisWidth=0.0055, lineWidth=0.0043/*0.0044/*0.0047*/, line1Width=0.0026/*0.003*/;
    int lineAxisColor=Color.rgb(100, 100, 100), lineColor=Color.rgb(160,160,160), line1Color=Color.rgb(160,160,160);

    public ViewDrawable(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        font=Typeface.createFromAsset(ActivityMain.assets, "liberation_serif_regular.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint lineAxisPaint=new Paint();
        lineAxisPaint.setColor(lineAxisColor);
        lineAxisPaint.setStrokeWidth((int)(w*lineAxisWidth));

        Paint linePaint=new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth((int)(w*lineWidth));

        Paint line1Paint=new Paint();
        line1Paint.setColor(line1Color);
        line1Paint.setStrokeWidth((int)(w*line1Width));

        Paint graphFontPaint=new Paint();
        graphFontPaint.setColor(Color.BLACK);
        graphFontPaint.setTypeface(font);
        graphFontPaint.setTextSize((int)(w*graphFontSize));

        Paint indicatorPaint=new Paint();
        indicatorPaint.setColor(Color.rgb(0,128,0));
        indicatorPaint.setTypeface(font);
        indicatorPaint.setTextSize((int)(w*indicatorFontSize));

        double graphX0=2*paddingMain*w, graphX1=w-paddingMain*w, graphY0=(h-2*paddingMain*w), graphY1=h/2+2*paddingMain*w, graphY1_=h/2+paddingMain*w;

        int scaleSpeedToPx=45;

        int i=0, y=(int)graphY0-scaleSpeedToPx;
        Paint paintCurrent=null;
        while(true)
        {
            y=(int)graphY0-scaleSpeedToPx*i;
            if(y<graphY1_) break;

            if(i==0)
            {
                paintCurrent=lineAxisPaint;
            }
            else if(i % 10==0)
            {
                paintCurrent=linePaint;
            }
            else if(i % 5==0)
            {
                paintCurrent=line1Paint;
            }
            else continue;

            canvas.drawLine((int)graphX0, y, (int)graphX1, y, paintCurrent);

            i+=5;
        }

        canvasDrawTextCenter(canvas, "12",w/2, h/4, indicatorPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        this.w=w;
        this.h=h;

        invalidate();
    }

    private void canvasDrawTextCenter(Canvas canvas, String text, int x, int y, Paint paint)
    {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, x-bounds.centerX(), y-bounds.centerY(), paint);
    }
}
