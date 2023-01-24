package com.example.clock;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

public class ClockSurfaceView extends SurfaceView implements Runnable {
    private Context c;
    private int length;

    private  boolean running;
    private SurfaceHolder holder=null;
    private  Thread thread=null;
    public int inewclr;
    public int clr;
//    String newclr=clr.hourhandchange();
    public ClockSurfaceView(Context context, int length ,int clr) {
        super(context);
        this.c = context;
        this.length = length;
        this.clr=clr;
        thread=new Thread(this);
        thread.start();;
        holder=this.getHolder();
        running=true;

    }

    public void onResume(){
        thread=new Thread(this);
        running=true;
        thread.start();
    }

    public void onPause(){
        running=false;
        boolean rentry=true;

        while(rentry){
            try{
                thread.join();
                rentry=false;
            }catch (Exception e){}
        }
    }

    @Override
    public void run(){


        Thread t=new Thread();
        boolean sect=true;
        boolean mint=true;
        int sec=0;
        int min=0;
        int hr=0;
        int milli=0;
        while(running){
            //test surface valid
            if(holder.getSurface().isValid()) {
//                if (newclr=="RED"){
//                    inewclr= Integer.parseInt("#FF0000");
//                }
//                else if(newclr=="YELLOW"){
//                    inewclr= Integer.parseInt("#FFFF00");
//                }
//                else{
//                    inewclr= Integer.parseInt("#0000FF");
//                }
                //draw within canvas from holder
                Canvas canvas = holder.lockCanvas();

                Paint paint1 = new Paint();
                paint1.setColor(Color.BLACK);//Background color
                canvas.drawPaint(paint1);
                paint1.setColor(Color.WHITE);//Marks color
                paint1.setStrokeWidth(10);

                Paint paint2 = new Paint();

//                paint2.setColor(inewclr);
                paint2.setColor(clr);
                paint2.setStrokeWidth(20);



                Paint paint3=new Paint();
                paint3.setColor(Color.WHITE);
                paint3.setTextAlign(Paint.Align.CENTER);
                paint3.setTextSize(70);
                paint3.setStrokeWidth(3);

                Calendar calendar = Calendar.getInstance();
                hr = calendar.get(Calendar.HOUR);

                min = calendar.get(Calendar.MINUTE);

                sec = calendar.get(Calendar.SECOND);
                milli=calendar.get(Calendar.MILLISECOND);


                RegPoly secMarks = new RegPoly(60, this.length+50, this.getWidth() / 2, this.getHeight() /2, canvas, paint1);
                RegPoly hourMarks = new RegPoly(12, this.length+50, this.getWidth() / 2, this.getHeight() /2, canvas, paint2);
                RegPoly hourMarksN = new RegPoly(12, this.length+50, this.getWidth()/ 2 , this.getHeight()/ 2, canvas, paint3);

                RegPoly milliHand = new RegPoly(60, this.length - 300, this.getWidth() / 2, this.getHeight() / 2, canvas, paint3);
                RegPoly secHand = new RegPoly(60, this.length - 30, this.getWidth() / 2, this.getHeight() / 2, canvas, paint3);
                RegPoly minHand = new RegPoly(60, this.length -150, this.getWidth() / 2, this.getHeight() / 2, canvas, paint2);
                RegPoly hourHand = new RegPoly(60, this.length - 200, this.getWidth() / 2, this.getHeight() / 2, canvas, paint2);

                secMarks.drawPoints();
                hourMarks.drawPoints();
                hourMarksN.drawNumbers();

                milliHand.drawRadiusC(milli+45);
                secHand.drawRadiusC(sec + 45);
                minHand.drawRadiusC(min + 45);
                hourHand.drawRadiusC(hr * 5 + min / 12+45);
                holder.unlockCanvasAndPost(canvas);

                try {


                    t.sleep(1000);




                } catch (Exception e) {
                }


            }
        }
    }


}
