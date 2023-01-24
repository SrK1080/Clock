package com.example.clock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class RegPoly {
    private float x0, y0, r;
    private int n;
    private float x[], y[];
    private Canvas c;
    private Paint p;
    int num;
    public RectF rectf;

    public RegPoly(int n, float r, float x0, float y0, Canvas canvas, Paint paint) {
        this.n=n;
        this.r=r;
        this.x0=x0;
        this.y0=y0;

        this.c=canvas;
        this.p=paint;

        x=new float[n];
        y=new float[n];

        for(int i=0;i<n;i++){
            x[i]=(float)(x0+r*Math.cos(2*Math.PI*i/n));
            y[i]=(float)(y0+r*Math.sin(2*Math.PI*i/n));

        }
        Log.d("x array","="+x);
    }
    public float getX(int i){return x[i%n];}

    public float getY(int i){return y[i%n];}

    public void drawRegPoly(){
        for(int i=0;i<n;i++){
            this.c.drawLine(x[i],y[i],x[(i+1)%n],y[(i+1)%n],p);
        }
    }

    public void drawPoints(){
        for (int i=0;i<n;i++){
            this.c.drawCircle(x[i],y[i],4,p);
        }
    }
    public void drawArc(int i){

            rectf=new RectF(this.x0-r,this.y0-r,this.x0+r,this.y0+r);
            this.c.drawArc(rectf,-90F,(360/60)*i,true,p);

    }

    public void drawNumbers(){
        num=3;
        float a1= 1240.0F;
        float b1=1352.0F;
        this.c.drawText(Integer.toString(num),a1,b1,p);

        num=4;
        float a2= 1170.3333F;
        float b2=1612.0F;
        this.c.drawText(Integer.toString(num),a2,b2,p);

        num=5;
        float a3= 980.0F;
        float b3=1802.3333F;
        this.c.drawText(Integer.toString(num),a3,b3,p);

        num=6;
        float a4= 720.0F;
        float b4=1872.0F;
        this.c.drawText(Integer.toString(num),a4,b4,p);

        num=7;
        float a5= 460.0F;
        float b5=1802.3333F;
        this.c.drawText(Integer.toString(num),a5,b5,p);

        num=8;
        float a6= 269.66678F;
        float b6=1612.0F;
        this.c.drawText(Integer.toString(num),a6,b6,p);

        num=9;
        float a7=  200.0F;
        float b7=1352.0F;
        this.c.drawText(Integer.toString(num),a7,b7,p);

        num=10;
        float a8= 269.66678F;
        float b8=1092.0F;
        this.c.drawText(Integer.toString(num),a8,b8,p);

        num=11;
        float a9= 460.0F;
        float b9=901.6668F;
        this.c.drawText(Integer.toString(num),a9,b9,p);

        num=12;
        float a10= 720.0F;
        float b10=832.0F;
        this.c.drawText(Integer.toString(num),a10,b10,p);

        num=1;
        float a11= 980.0F;
        float b11=901.6668F;
        this.c.drawText(Integer.toString(num),a11,b11,p);

        num=2;
        float a12= 1170.3333F;
        float b12=1092.0F;
        this.c.drawText(Integer.toString(num),a12,b12,p);






    }


    public void drawRadiusC(int i){
        this.c.drawLine(x0,y0,getX(i),getY(i),p);
    }
    public void drawRadiusT(int i){
        this.c.drawLine(x0,y0,x[i%n],y[i%n],p);
    }

}

