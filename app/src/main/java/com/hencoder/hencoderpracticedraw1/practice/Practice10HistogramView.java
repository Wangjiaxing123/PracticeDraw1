package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {
    private static final String TAG = "Practice10HistogramView>>>>";

    public Practice10HistogramView(Context context) {
        this(context,null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setAntiAlias(true);
        initDatas();
    }

    private Paint paint;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
//        画坐标
        drawZuoBiao(canvas);
        drawRects(canvas,dates);
        drawText(canvas,dates);
    }


    private void drawZuoBiao(Canvas canvas){
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);

        Path path=new Path();
        path.moveTo(100,40);
        path.lineTo(100,400);
        path.lineTo(600,400);
        canvas.drawPath(path,paint);
    }


    private void drawRects(Canvas canvas,List<Data> datas){
        int count=datas.size();
        int split=10;
        int recWidth=((500-split)/count)-split;//柱形的宽
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        for(int i=0;i<datas.size();i++){
            int left1=(i+1)*split+(i*recWidth)+100;
            int left2=400-datas.get(i).value;
            int right1=(i+1)*(split+recWidth)+100;
            int right2=400;
            Rect rect=new Rect(left1,left2,right1,right2);
            canvas.drawRect(rect,paint);
        }
    }

    private void drawText(Canvas canvas,List<Data> datas){
        paint.setColor(Color.WHITE);
        int all=500;
        int ave=all/7;
        int split=ave/2-10;
        int y=420;
        for (int i=0;i<datas.size();i++){
            int x=split+(i*ave)+100;
            canvas.drawText(datas.get(i).title,x,y,paint);
        }
    }


    private List<Data>  dates;

    private void initDatas(){
        dates=new ArrayList<>();
        dates.add(Data.getInstance(3,"froyo"));
        dates.add(Data.getInstance(20,"GB"));
        dates.add(Data.getInstance(20,"ICS"));
        dates.add(Data.getInstance(140,"JB"));
        dates.add(Data.getInstance(250,"KitKat"));
        dates.add(Data.getInstance(290,"L"));
        dates.add(Data.getInstance(200,"M"));
    }

    public static class Data{
       public int value;
       public String title;

        public Data(int value, String title) {
            this.value = value;
            this.title = title;
        }

        public static Data getInstance(int value, String title){
            return ( new Data(value,title));
        }
    }



}
