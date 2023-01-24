package com.example.clock;

import static android.content.Intent.getIntent;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.logging.LogRecord;

public class TimerSurfaceView extends SurfaceView implements Runnable {


    private float length;
    private Thread thread;
    public boolean running = true;
    private SurfaceHolder holder;
    public timerview time;
    int secs;
    int clr;
    Context context ;

    Toast toast = Toast.makeText(getContext(), "Timer Stopped", Toast.LENGTH_SHORT);


    public TimerSurfaceView(Context context, float length, int secs,int clr) {
        super(context);
        this.clr=clr;
        this.length = length;
        this.secs = secs;
        this.holder = getHolder();
    }

    public void onResumeClock() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void onPauseClock() {
        running = false;
        boolean reentry = true;
        while (reentry) {
            try {
                thread.join();
                reentry = false;
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void run() {

        int sec = 0;

        while (running) {
            if (holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(5);
                canvas.drawPaint(paint);

                // draw the marks
                paint.setColor(clr);
                RegPoly secMarks = new RegPoly(100, this.length+50, this.getWidth() / 2, this.getHeight() /2, canvas, paint);

                RegPoly secHand = new RegPoly(secs, this.length - 30, this.getWidth() / 2, this.getHeight() / 2, canvas, paint);

                secMarks.drawPoints();


                secHand.drawArc(sec);

                holder.unlockCanvasAndPost(canvas);
                // sleep for 1 sec
                try {
                    Thread.sleep(1000*secs/60);
                    sec++;
                } catch (Exception e) {
                }

                if (sec == 61) {
                    running = false;
                    toast.show();

                }
            }
        }
    }
}